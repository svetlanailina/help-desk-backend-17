package ru.svetlanailina.helpdesk.backend17.service;

import org.springframework.stereotype.Service;
import ru.svetlanailina.helpdesk.backend17.entity.Priority;
import ru.svetlanailina.helpdesk.backend17.repo.PriorityRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// всегда нужно создавать отдельный класс Service для доступа к данным, даже если кажется,
// что мало методов или это все можно реализовать сразу в контроллере
// Такой подход полезен для будущих доработок и правильной архитектуры (особенно, если работаете с транзакциями)
@Service

// все методы класса должны выполниться без ошибки, чтобы транзакция завершилась
// если в методе возникнет исключение - все выполненные операции из данного метода откатятся (Rollback)
@Transactional
public class PriorityService {

    private final PriorityRepository repository; // сервис имеет право обращаться к репозиторию (БД)

    public PriorityService(PriorityRepository repository) {
        this.repository = repository;
    }

    public List<Priority> findAll(String email) {
        return repository.findByUserEmailOrderByIdAsc(email);
    }

    public Priority add(Priority priority) {
        return repository.save(priority); // метод save обновляет или создает новый объект, если его не было
    }

    public Priority update(Priority priority) {
        return repository.save(priority); // метод save обновляет или создает новый объект, если его не было
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Optional<Priority> findById(Long id) {
        return repository.findById(id); // т.к. возвращается Optional - можно получить объект методом get()
    }

    public List<Priority> find(String title, String email) {
        return repository.findByTitle(title, email);
    }

}
