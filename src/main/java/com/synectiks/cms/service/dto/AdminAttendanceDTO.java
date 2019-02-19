package com.synectiks.cms.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.AttendanceStatusEnum;

/**
 * A DTO for the AdminAttendance entity.
 */
public class AdminAttendanceDTO implements Serializable {

    private Long id;

    @NotNull
    private AttendanceStatusEnum attendanceStatus;

    @NotNull
    private String comments;

    private Long studentId;

    private Long lectureId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AttendanceStatusEnum getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(AttendanceStatusEnum attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getLectureId() {
        return lectureId;
    }

    public void setLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdminAttendanceDTO adminAttendanceDTO = (AdminAttendanceDTO) o;
        if (adminAttendanceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adminAttendanceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdminAttendanceDTO{" +
            "id=" + getId() +
            ", attendanceStatus='" + getAttendanceStatus() + "'" +
            ", comments='" + getComments() + "'" +
            ", student=" + getStudentId() +
            ", lecture=" + getLectureId() +
            "}";
    }
}