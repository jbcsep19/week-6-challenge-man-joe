package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    CarRepository carRepository;
    @Autowired
    CategorieRepository categorieRepository;

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("categories", categorieRepository.findAll());
        return "index";
    }

    @GetMapping("/addcar")
    public String carForm(Model model){
        model.addAttribute("categories", categorieRepository.findAll());
        model.addAttribute("car", new Car());
        return "carform";
    }

    @PostMapping("/process_car")
    public String processCarForm(@ModelAttribute Car car, BindingResult result){
        carRepository.save(car);
        return "redirect:/";
    }

    @GetMapping("/addcategorie")
    public String categorieForm(Model model){
        model.addAttribute("categorie", new Categorie());
        return "categorieform";
    }

    @PostMapping("/process_categorie")
    public String processCategorieForm(@ModelAttribute Categorie categorie, BindingResult result){
        categorieRepository.save(categorie);
        return "redirect:/";
    }

    @RequestMapping("/update_categorie/{id}")
    public String updateCategorie(@PathVariable("id") long id, Model model){
        model.addAttribute("categorie", categorieRepository.findById(id).get());
        return "categorieform";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") long id){
        carRepository.deleteById(id);
        System.out.println("ID = " + id + carRepository.existsById(id));
        return "redirect:/";
    }

    @RequestMapping("/delete_categorie/{id}")
    public String delCategorie(@PathVariable("id") long id){
        categorieRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/update_car/{id}")
    public String updateCar(@PathVariable("id") long id, Model model){
        model.addAttribute("categories" , categorieRepository.findAll());
        model.addAttribute("car", carRepository.findById(id).get());
        return "carform";
    }

    @RequestMapping("/detail_car/{id}")
    public String showCar(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        return "cardetail";
    }

}
