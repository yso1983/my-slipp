package net.slipp.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Question extends AbstractEntity {
	@Id
	@GeneratedValue
	private Long id;
	
	private String writer;
	
	private String title;
	
	private String Contents;

	public Question() {}

	public Question(String writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		Contents = contents;
	}

	public Long getId() { 
		return id;
	}
	
	
//	@ManyToOne
//	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
//	@JsonProperty
//	private User writer;
//	
//	@JsonProperty
//	private String title;
//	
//	@Lob
//	@JsonProperty
//	private String contents;
//	
//	@JsonProperty
//	private Integer countOfAnswer = 0; 
//	
//	@OneToMany(mappedBy="question")
//	@OrderBy("id DESC")
//	private List<Answer> answers;
//	
//	public Question() {}
//	
//	public Question(User writer, String title, String contents) {
//		this.writer = writer;
//		this.title = title;
//		this.contents = contents;
//	}
//	
//	public void update(String title, String contents) {
//		this.title = title;
//		this.contents = contents;
//	}
//
//	public boolean isSameWriter(User loginUser) {
//		System.out.println("writer : " + writer);
//		return this.writer.equals(loginUser);
//	}
//	
//	public void addAnswer() {
//		this.countOfAnswer += 1;
//	}
//	
//	public void deleteAnswer() {
//		this.countOfAnswer -= 1;
//	}
}