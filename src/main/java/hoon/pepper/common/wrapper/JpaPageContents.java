package hoon.pepper.common.wrapper;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public abstract class JpaPageContents<I, O> extends PageContents<O> {

	public JpaPageContents(Page<I> page) {
		this.setOffset(page.getNumber() + 1);
		if (page.getSize() == Integer.MAX_VALUE) {
			this.setLimit(0);
		} else {
			this.setLimit(page.getSize());
		}
		this.setTotal((int)page.getTotalElements());
		this.convert(page.getContent());
	}

	public void convert(List<I> contents) {
		List<O> outContent = new ArrayList<>();

		for (I input : contents) {
			O output = this.converts(input);
			outContent.add(output);
		}
		this.setContents(outContent);
	}

	public abstract O converts(I content);
}
