package com.practicaweb.practicadaw.repository;

import com.practicaweb.practicadaw.model.Comment;
import com.practicaweb.practicadaw.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "DELETE FROM webapp8_bbdd.comment WHERE user_id_user = :idUser", nativeQuery = true)
    void deleteCommentByIdUser(@Param("idUser") long idUser);

    @Query(value = "DELETE FROM webapp8_bbdd.comment WHERE entry_id_entry = :idEntry", nativeQuery = true)
    void deleteCommentByIdEntry(@Param("idEntry") long idEntry);

}
