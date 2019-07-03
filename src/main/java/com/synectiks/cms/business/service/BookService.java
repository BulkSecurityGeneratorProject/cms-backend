package com.synectiks.cms.business.service;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.filter.Book.BookListFilterInput;
import com.synectiks.cms.repository.AddNewBookRepository;
import com.synectiks.cms.service.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookService {
    @Autowired
    AddNewBookRepository addNewBookRepository;

    @Autowired
    CommonService commonService;

    public List<AddNewBook> searchBook(String bookTitle, String author, Long departmentId, Long batchId, Long subjectId) {
        AddNewBook book = new AddNewBook();

        if (bookTitle != null) {
            book.setBookTitle(bookTitle);
        }

        if (author != null) {
            book.setAuthor(author);
        }
        if (departmentId != null) {
            Department department = new Department();
            department.setId(departmentId);
            book.setDepartment(department);
        }
        if (batchId != null) {
            Batch batch = new Batch();
            batch.setId(batchId);
            book.setBatch(batch);
        }
        if (subjectId != null) {
            Subject subject = new Subject();
            subject.setId(subjectId);
            book.setSubject(subject);
        }
        Example<AddNewBook> example = Example.of(book);
        List<AddNewBook> list = this.addNewBookRepository.findAll(example);
        return list;
    }

    public List<AddNewBook> searchBook(BookListFilterInput filter) {
        AddNewBook book = new AddNewBook();
        if (!CommonUtil.isNullOrEmpty(filter.getDepartmentId())) {
            Department department = this.commonService.getDepartmentById(Long.valueOf(filter.getDepartmentId()));
            if (department != null) {
                book.setDepartment(department);
            }
        }
        if (!CommonUtil.isNullOrEmpty(filter.getBatchId())) {
            Batch batch = this.commonService.getBatchById(Long.valueOf(filter.getBatchId()));
            if (batch != null) {
                book.setBatch(batch);
            }

            if (!CommonUtil.isNullOrEmpty(filter.getSubjectId())) {
                Subject subject = this.commonService.getSubjectById(Long.valueOf(filter.getSubjectId()));
                if (subject != null) {
                    book.setSubject(subject);
                }


            }
        }
        Example<AddNewBook> example = Example.of(book);
        List<AddNewBook> list = this.addNewBookRepository.findAll(example);
        return list;
    }
}