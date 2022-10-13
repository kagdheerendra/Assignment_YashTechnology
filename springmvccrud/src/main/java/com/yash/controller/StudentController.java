package com.yash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yash.beans.Student;
import com.yash.dao.StudentDao;

@Controller
public class StudentController {
 
	@Autowired
	StudentDao sDao;
	
	@RequestMapping("/studentform")
	public String showform(Model m){
		m.addAttribute("command", new Student());
		return "studentform";
	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@ModelAttribute("student") Student student){
		sDao.save(student);
		return "redirect:/viewstudent";
	}
	
    @RequestMapping(value="/editstudent/{id}")  
    public String edit(@PathVariable int id, Model m){  
        Student s = sDao.getStudentById(id); 
        m.addAttribute("command",s);
        return "studenteditform";  
    } 
    
    @RequestMapping(value="/editsave",method = RequestMethod.POST)  
    public String editsave(@ModelAttribute("s") Student s){  
        sDao.update(s);  
        return "redirect:/viewstudent";  
    }  
    
    @RequestMapping(value="/deletestudent/{id}",method = RequestMethod.GET)  
    public String delete(@PathVariable int id){  
        sDao.delete(id);  
        return "redirect:/viewstudent";  
    } 
    
    @RequestMapping("/viewstudent")  
    public String viewemp(Model m){  
        List<Student> list=sDao.getStudents();
        m.addAttribute("slist",list);
        return "viewstudent";  
    } 
}
