package copy_2ch;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Responce {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	
	private Integer res_id;
	
	private String contents;
	
	private String name="ななしさん";
	
	private LocalDateTime postDateTime;
	
	@ManyToOne
	private Thread thread;
	
	
	
}
