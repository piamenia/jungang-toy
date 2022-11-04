package hoon.pepper.conti.service;

import hoon.pepper.conti.persistence.entity.CategoryEntity;
import hoon.pepper.conti.persistence.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryService {
	private final CategoryRepository categoryRepository;

	public List<CategoryEntity> getCategoryList() {
		return categoryRepository.findAll();
	}
}
