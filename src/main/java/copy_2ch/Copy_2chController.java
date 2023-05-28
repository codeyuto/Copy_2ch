package copy_2ch;



import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class Copy_2chController {
	@PersistenceContext
    EntityManager em;
	
	@Autowired
	private ThreadRepository threadRepository;
	
	@Autowired
	private ResponceRepository responceRepository;
	
	@GetMapping("/")
	public String index(Model model) {
		List<Thread> threads=threadRepository.findAll();
		model.addAttribute("threads",threads);
		model.addAttribute("all_threads",threads.size());
		model.addAttribute("result_threads",null);
		model.addAttribute("word","");
		return "index";
	}
	
	@GetMapping("/form")
	public String form(Thread thread){
	return "form";
	}
	@PostMapping("/create")
	public String create(Thread thread) {
		thread.setPostDateTime(LocalDateTime.now());
		threadRepository.save(thread);
		return "redirect:/thread/"+thread.getId();
	}
	
	 @GetMapping("/thread/{id}")
	    public String thread(@PathVariable Integer id, Model model) {
	        Optional<Thread> thread = threadRepository.findById(id);
	        model.addAttribute("thread", thread.get());
	        model.addAttribute("id",id);

	        Responce responce = new Responce();
	        responce.setThread(thread.get());
	        model.addAttribute("responce", responce);
	        return "thread";
	    }
	
	 @PostMapping("/responce")
	    public String createResponce(Responce responce) {
		    Thread thread=responce.getThread();
	        responce.setPostDateTime(LocalDateTime.now());
	        thread.setRes_count(thread.getRes_count()+1);
	        responce.setRes_id(thread.getRes_count()+thread.getRes_id());
	        responceRepository.save(responce);
	        return "redirect:/thread/" + responce.getThread().getId();
	    }
	 
	 
	 @PostMapping("/search")
	 public String search(String word,Model model) {
		
		List<Thread> resultList=threadRepository.findByTitleLike("%"+word+"%");
		List<Thread> threads=threadRepository.findAll();
		 if(resultList.isEmpty()) {
			 
			 model.addAttribute("result_threads",null);
			 model.addAttribute("threads",threads);
			 model.addAttribute("word",word);
			 model.addAttribute("isEmpty",true);
			 
		 }else {
				
			 
			 model.addAttribute("result_threads",resultList);
			 model.addAttribute("threads",threads);
			 model.addAttribute("word",word);
			 model.addAttribute("isEmpty",false);
			 
		 }
		 
		 return "search";
	 }
		 
		
	 

}
