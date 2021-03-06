package com.synectiks.cms.filter.studentattendance;

import com.synectiks.cms.domain.Student;

public class DailyAttendanceVo {
	
	private String studentId;
	private String studentName;
	private String currentDateStatus;
	private String previousOneDayStatus;
	private String previousTwoDayStatus;
	private String previousThreeDayStatus;
	private String comments;
	private Student student;
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getCurrentDateStatus() {
		return currentDateStatus;
	}
	public void setCurrentDateStatus(String currentDateStatus) {
		this.currentDateStatus = currentDateStatus;
	}
	public String getPreviousOneDayStatus() {
		return previousOneDayStatus;
	}
	public void setPreviousOneDayStatus(String previousOneDayStatus) {
		this.previousOneDayStatus = previousOneDayStatus;
	}
	public String getPreviousTwoDayStatus() {
		return previousTwoDayStatus;
	}
	public void setPreviousTwoDayStatus(String previousTwoDayStatus) {
		this.previousTwoDayStatus = previousTwoDayStatus;
	}
	public String getPreviousThreeDayStatus() {
		return previousThreeDayStatus;
	}
	public void setPreviousThreeDayStatus(String previousThreeDayStatus) {
		this.previousThreeDayStatus = previousThreeDayStatus;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
//	@Override
//	public String toString() {
//		return "DailyAttendance [studentName=" + studentName + ", currentDateStatus=" + currentDateStatus
//				+ ", previousOneDayStatus=" + previousOneDayStatus + ", previousTwoDayStatus=" + previousTwoDayStatus
//				+ ", previousThreeDayStatus=" + previousThreeDayStatus + ", comments=" + comments + "]";
//	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	@Override
	public String toString() {
		return "DailyAttendanceVo [studentId=" + studentId + ", studentName=" + studentName + ", currentDateStatus="
				+ currentDateStatus + ", previousOneDayStatus=" + previousOneDayStatus + ", previousTwoDayStatus="
				+ previousTwoDayStatus + ", previousThreeDayStatus=" + previousThreeDayStatus + ", comments=" + comments
				+ ", student=" + student + "]";
	}
	
}
