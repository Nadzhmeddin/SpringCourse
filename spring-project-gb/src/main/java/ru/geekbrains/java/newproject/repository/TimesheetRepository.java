package ru.geekbrains.java.newproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.java.newproject.model.Timesheet;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

    // Простые запросы:
    // select * from timesheet where project_id = $1
    // Note: сломается если в БД результат выдает больше одного значения
//    Optional<Timesheet> findByProjectId(Long projectId);

    // select * from timesheet where projectId = $1
//    List<Timesheet> findByProjectId(Long projectId);

    // select * from timesheet where project_id = $1 or minutes = $2
    List<Timesheet> findByProjectIdOrMinutes(Long projectId, Integer minutes);

    // select * from timesheet where created_at > $1 and created_at < $2
    List<Timesheet> findByCreatedAtBetween(LocalDate min, LocalDate max);

    // select * from timesheet where project_id = null;
    List<Timesheet> findByProjectIdIsNull();

    // Сложные запросы : Query Methods

    // select * from timesheet where project_id = $1
    // order by created_at desc

    // запрос пишется на JQL - java query language
    // select * from timesheet t where t.projectId = :projectId order by t.created_at desc
    @Query("select t from Timesheet t where t.projectId = :projectId order by t.createdAt desc")
    List<Timesheet> findByProjectId(Long projectId);

    @Query(nativeQuery = true, value = "select id from timesheet where project_id = :projectId")
    List<Long> findIdsByProjectId(Long projectId);

    @Query(nativeQuery = true, value = "update timesheet set active = false where project_id = :projectId")
    @Modifying
    void deactiveTimesheetsWithProjectId(Long projectId);

    List<Timesheet> findByEmployeeId(Long employeeId);
}
