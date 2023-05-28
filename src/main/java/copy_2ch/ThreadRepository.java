package copy_2ch;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ThreadRepository extends JpaRepository<Thread,Integer> {
	public List<Thread> findByTitleLike(String title);

}
