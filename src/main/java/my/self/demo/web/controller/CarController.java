package my.self.demo.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import my.self.demo.data.cars.CarModelDataRepository;
import my.self.demo.domain.CarService;
import my.self.demo.domain.model.CarModel;
import my.self.demo.web.util.CarImageService;

@Controller
public class CarController {

	@Autowired
	private CarService carService;

	@Autowired
	private CarImageService carImageService;
	
	@Autowired
	private CarModelDataRepository carModelDataRepository;
	
	@GetMapping("car/list")
	public String list(Model model) {
		model.addAttribute("cars", carService.getCarsList());
		model.addAttribute("title", "Список автомобилей (демонстрация Spring JDBC)");
		
		return "car/list";
	}
	
	@GetMapping("car/list-data")
	public ModelAndView listData(ModelAndView model) {
		model.addObject("cars", carModelDataRepository.findAllByNameBrand("Tes%"));
		model.addObject("title", "Список автомобилей (демонстрация Spring Data JDBC)");

		model.setViewName("car/list-data");
		
		return model;
	}

	@GetMapping(value = {"car/page", "car/page/{pageId}"})
	public String list(Model model, @PathVariable Optional<Integer> pageId) {
		int page = pageId.isPresent() ? pageId.get() : 0;
		
		Page<CarModel> cars = carService.findAll(PageRequest.of(page, 3, Sort.by("name").ascending()));

		model.addAttribute("cars", cars);

		return "car/page";
	}
	
    @GetMapping(value="car/img-big/{carId}", produces=MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public FileSystemResource bigAvatar(ModelAndView modelAndView, @PathVariable Long carId) {
        return carImageService.getImage(carId, CarImageService.BIG_AVATAR_POSTFIX);
    }
    
    
    @GetMapping(value="car/img-small/{carId}", produces=MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public FileSystemResource smallAvatar(ModelAndView modelAndView, @PathVariable Long carId) {
        return carImageService.getImage(carId, CarImageService.SMALL_AVATAR_POSTFIX);
    }
        
    @GetMapping("/car/img-upload")
    public ModelAndView avatarUpload(ModelAndView modelAndView, @RequestParam("carId") Optional<Long> carId) {
    	if (carId.isEmpty()) {
    		modelAndView.setViewName("redirect:/car/page");
    	} else {
    		modelAndView.addObject("carId", carId.get());
    		modelAndView.setViewName("/car/img-upload");
    	}
        return modelAndView;
    }

    @PostMapping("/car/img-upload")
    public ModelAndView avatarUploadProcessing(@RequestParam("files") MultipartFile[] files, 
    		@RequestParam("carId") Optional<Long> carId, 
    		ModelAndView modelAndView, Authentication auth) 
    {
    
        modelAndView.setViewName("redirect:/car/page");
        
        if (carId.isPresent()) {
        	CarModel car = carService.findById(carId.get());
        	
        	if (car != null) {
                for (MultipartFile multipartFile : files) {
                	if (!carImageService.saveCarImage(multipartFile, carId.get())) {
                		modelAndView.setViewName("redirect:/car/upload-error");
                		break;
                	}
                }
        	}
        }
        
        return modelAndView;
    }       
	
    @GetMapping("/car/upload-error")
    public String uploadError() {
        return "/car/upload-error";
    }
	
}
