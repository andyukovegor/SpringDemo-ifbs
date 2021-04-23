package my.self.demo.web.util;

import java.io.IOException;
import java.io.File;
import java.util.logging.Logger;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

@Service
public class CarImageService {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	public static String BIG_AVATAR_POSTFIX = "_big_thumb.png";
	public static String SMALL_AVATAR_POSTFIX = "_small_thumb.png";

	@Value("${project.car.image.dir.path}")
	private String carImgDirPath;

	public FileSystemResource getImage(Long id, String postfix) {
		String avatarFileName = carImgDirPath + File.separator + id + File.separator + id + postfix;

		File f = new File(avatarFileName);
		if (f.exists() && !f.isDirectory()) {
			return new FileSystemResource(f);
		} else {
			try {
				f = new ClassPathResource("/static/img/car" + postfix).getFile();
				if (f.exists() && !f.isDirectory()) {
					return new FileSystemResource(f);
				}
			} catch (IOException e) {
				logger.severe(e.getMessage());
			}
		}

		return null;
	}

	public boolean saveCarImage(MultipartFile multipartFile, Long carId) {
		boolean result = true;
		String filePath = carImgDirPath + File.separator + carId + File.separator;

		if (!(new File(filePath).exists())) {
			new File(filePath).mkdirs();
		}

		try {
			FileUtils.cleanDirectory(new File(filePath));

			String orgName = multipartFile.getOriginalFilename();
			String fullFilePath = filePath + orgName;

			File dest = new File(fullFilePath);
			multipartFile.transferTo(dest);

			Thumbnails.of(dest).size(80, 80).crop(Positions.CENTER)
					.toFile(new File(filePath + carId + BIG_AVATAR_POSTFIX));
			Thumbnails.of(dest).size(35, 35).crop(Positions.CENTER)
					.toFile(new File(filePath + carId + SMALL_AVATAR_POSTFIX));

		} catch (IllegalStateException e) {
			logger.severe(e.getMessage());
			result = false;
		} catch (IOException e) {
			logger.severe(e.getMessage());
			result = false;
		}

		return result;
	}
}