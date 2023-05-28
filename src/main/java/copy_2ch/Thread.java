package copy_2ch;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.Data;

@Entity
@Data
public class Thread {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private Integer res_id=1;
	
	private Integer res_count=0;
	
	private LocalDateTime postDateTime;
	
	private String title;
	
	private String name="ななしさん";
	
	@Column(length=1000)
	private String contents;
	
	@OneToMany(mappedBy="thread")
	private List<Responce> responces;
	
	

	
	
}
	
