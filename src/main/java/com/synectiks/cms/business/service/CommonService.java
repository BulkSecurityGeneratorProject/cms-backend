package com.synectiks.cms.business.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.synectiks.cms.config.GlobalConfig;
import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.AcademicExamSetting;
import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.AttendanceMaster;
import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.domain.City;
import com.synectiks.cms.domain.CmsAcademicYearVo;
import com.synectiks.cms.domain.CmsCourseEnumVo;
import com.synectiks.cms.domain.CmsFacility;
import com.synectiks.cms.domain.CmsFeeCategory;
import com.synectiks.cms.domain.CmsFeeDetails;
import com.synectiks.cms.domain.CmsGenderVo;
import com.synectiks.cms.domain.CmsNotificationsVo;
import com.synectiks.cms.domain.CmsSemesterVo;
import com.synectiks.cms.domain.CmsStudentTypeVo;
import com.synectiks.cms.domain.CmsTermVo;
import com.synectiks.cms.domain.College;
import com.synectiks.cms.domain.Config;
import com.synectiks.cms.domain.Department;
import com.synectiks.cms.domain.Employee;
import com.synectiks.cms.domain.Facility;
import com.synectiks.cms.domain.FeeCategory;
import com.synectiks.cms.domain.FeeDetails;
import com.synectiks.cms.domain.Holiday;
import com.synectiks.cms.domain.Lecture;
import com.synectiks.cms.domain.Notifications;
import com.synectiks.cms.domain.Section;
import com.synectiks.cms.domain.State;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.domain.StudentAttendance;
import com.synectiks.cms.domain.Subject;
import com.synectiks.cms.domain.Teach;
import com.synectiks.cms.domain.Teacher;
import com.synectiks.cms.domain.Term;
import com.synectiks.cms.domain.TransportRoute;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.graphql.types.Student.Semester;
import com.synectiks.cms.graphql.types.Student.StudentType;
import com.synectiks.cms.graphql.types.course.Course;
import com.synectiks.cms.graphql.types.gender.Gender;
import com.synectiks.cms.repository.AcademicExamSettingRepository;
import com.synectiks.cms.repository.AcademicYearRepository;
import com.synectiks.cms.repository.AttendanceMasterRepository;
import com.synectiks.cms.repository.BatchRepository;
import com.synectiks.cms.repository.BranchRepository;
import com.synectiks.cms.repository.CityRepository;
import com.synectiks.cms.repository.CollegeRepository;
import com.synectiks.cms.repository.DepartmentRepository;
import com.synectiks.cms.repository.EmployeeRepository;
import com.synectiks.cms.repository.HolidayRepository;
import com.synectiks.cms.repository.NotificationsRepository;
import com.synectiks.cms.repository.SectionRepository;
import com.synectiks.cms.repository.StateRepository;
import com.synectiks.cms.repository.StudentAttendanceRepository;
import com.synectiks.cms.repository.StudentRepository;
import com.synectiks.cms.repository.SubjectRepository;
import com.synectiks.cms.repository.TeachRepository;
import com.synectiks.cms.repository.TeacherRepository;
import com.synectiks.cms.repository.TermRepository;
import com.synectiks.cms.repository.TransportRouteRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;

@Component
public class CommonService {

    private final static Logger logger = LoggerFactory.getLogger(CommonService.class);

    @Autowired
    private AcademicYearRepository academicYearRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeachRepository teachRepository;

    @Autowired
    private AttendanceMasterRepository attendanceMasterRepository;

    @Autowired
    private HolidayRepository holidayRepository;

    @Autowired
    private TermRepository termRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private AcademicExamSettingRepository academicExamSettingRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TransportRouteRepository transportRouteRepository;
    
    @Autowired
    private StudentAttendanceRepository studentAttendanceRepository;
    
//    @Autowired
//    private StudentRepository studentRepository;

    @Autowired
    private NotificationsRepository notificationsRepository;
    
    public AcademicYear findAcademicYearByYear(String academicYear) {
        if(CommonUtil.isNullOrEmpty(academicYear)) {
            return null;
        }
        AcademicYear ay = new AcademicYear();
        ay.setYear(academicYear);
        Example<AcademicYear> example = Example.of(ay);
        Optional<AcademicYear> acd = this.academicYearRepository.findOne(example);
        if(acd.isPresent()) {
            return acd.get();
        }
        return null;
    }

    public AcademicYear getAcademicYearById(Long academicYearId) {
        if(academicYearId == null) {
            return null;
        }
        Optional<AcademicYear> newAy = this.academicYearRepository.findById(academicYearId);
        if(newAy.isPresent()) {
            return newAy.get();
        }
        return null;
    }

    public AcademicYear getActiveAcademicYear() {
    	AcademicYear ay = new AcademicYear();
    	ay.setStatus(Status.ACTIVE);
        Optional<AcademicYear> newAy = this.academicYearRepository.findOne(Example.of(ay));
        if(newAy.isPresent()) {
            return newAy.get();
        }
        return null;
    }
    
    public Department getDepartmentById(Long departmentId) {
        if(departmentId == null) {
            return null;
        }
        Optional<Department> newDt = this.departmentRepository.findById(departmentId);
        if(newDt.isPresent()) {
            return newDt.get();
        }
        return null;
    }

    public TransportRoute getTransportRouteById(Long transportRouteId) {
        if(transportRouteId == null) {
            return null;
        }
        Optional<TransportRoute> newTr = this.transportRouteRepository.findById(transportRouteId);
        if(newTr.isPresent()) {
            return newTr.get();
        }
        return null;
    }

    public AcademicExamSetting getAcademicExamSettingById(Long academicExamSettingId) {
        if(academicExamSettingId == null) {
            return null;
        }
        Optional<AcademicExamSetting> newAs = this.academicExamSettingRepository.findById(academicExamSettingId);
        if(newAs.isPresent()) {
            return newAs.get();
        }
        return null;
    }

    public Batch getBatchById(Long batchId) {
        if(batchId == null) {
            return null;
        }
        Optional<Batch> newBt = this.batchRepository.findById(batchId);
        if(newBt.isPresent()) {
            return newBt.get();
        }
        return null;
    }

    public Teacher getTeacherById(Long teacherId) {
        if(teacherId == null) {
            return null;
        }
        Optional<Teacher> newTh = this.teacherRepository.findById(teacherId);
        if(newTh.isPresent()) {
            return newTh.get();
        }
        return null;
    }

    public Section getSectionById(Long secId) {
        if(secId == null) {
            return null;
        }
        Optional<Section> newSc = this.sectionRepository.findById(secId);
        if(newSc.isPresent()) {
            return newSc.get();
        }
        return null;
    }

    public Subject getSubjectById(Long subId) {
        if(subId == null) {
            return null;
        }
        Optional<Subject> newSb = this.subjectRepository.findById(subId);
        if(newSb.isPresent()) {
            return newSb.get();
        }
        return null;
    }

    public Teach getTeachBySubjectAndTeacherId(Long thrId, Long subId) {
        if(thrId == null || subId == null) {
            return null;
        }
        Teach th = new Teach();
        Subject s = getSubjectById(subId);
        Teacher t = getTeacherById(thrId);
        th.setSubject(s);
        th.setTeacher(t);
        Example<Teach> example = Example.of(th);
        Optional<Teach> newTh = this.teachRepository.findOne(example);
        if(newTh.isPresent()) {
            return newTh.get();
        }
        return null;
    }

    public AttendanceMaster getAttendanceMasterByBatchSectionTeach(Batch bt, Section sc, Teach th) {
        AttendanceMaster am = new AttendanceMaster();
        am.setBatch(bt);
        am.setSection(sc);
        am.setTeach(th);
        Example<AttendanceMaster> example = Example.of(am);
        Optional<AttendanceMaster> newAm = this.attendanceMasterRepository.findOne(example);
        if(newAm.isPresent()) {
            return newAm.get();
        }
        return null;
    }

    public List<AttendanceMaster> getAttendanceMasterByBatchSection(Batch bt, Section sc) {
        AttendanceMaster am = new AttendanceMaster();
        am.setBatch(bt);
        am.setSection(sc);
        Example<AttendanceMaster> example = Example.of(am);
        List<AttendanceMaster> newAm = this.attendanceMasterRepository.findAll(example);
        return newAm;
    }

    public AttendanceMaster getAttendanceMasterById(Long id) {
        if(id == null) {
            return null;
        }
        Optional<AttendanceMaster> newAm = this.attendanceMasterRepository.findById(id);
        if(newAm.isPresent()) {
            return newAm.get();
        }
        return null;
    }

    public List<Holiday> getHolidayList(Optional<AcademicYear> oay) {
//		AcademicYear acd = findAcademicYearByYear(academicYear);
        if(!oay.isPresent()) {
            logger.warn("Academic Year is null. Returning empty holiday list.");
            return Collections.emptyList();
        }
        Holiday hl = new Holiday();
        hl.setHolidayStatus(Status.ACTIVE);
        hl.setAcademicyear(oay.get());
        Example<Holiday> example = Example.of(hl);
        List<Holiday> list = this.holidayRepository.findAll(example);
        return list;
    }

    public Term getTermById(Long termId) {
        if(termId == null) {
            return null;
        }
        Term tm = new Term();
        tm.setTermStatus(Status.ACTIVE);
        tm.setId(termId);
        Example<Term> example = Example.of(tm);
        Optional<Term> term = this.termRepository.findOne(example);
        if(term.isPresent()) {
            return term.get();
        }
        return null;
    }

    public College getCollegeById(Long id) {
        if(id == null) {
            return null;
        }
        Optional<College> clg =  this.collegeRepository.findById(id);
        if(clg.isPresent()) {
            return clg.get();
        }
        return null;
    }

    public State getStateById(Long id) {
        if(id == null) {
            return null;
        }
        Optional<State> st =  this.stateRepository.findById(id);
        if(st.isPresent()) {
            return st.get();
        }
        return null;
    }

    public City getCityById(Long id) {
        if(id == null) {
            return null;
        }
        Optional<City> ct =  this.cityRepository.findById(id);
        if(ct.isPresent()) {
            return ct.get();
        }
        return null;
    }
    public Branch getBranchById(Long id) {
        if(id == null) {
            return null;
        }
        Optional<Branch> bt =  this.branchRepository.findById(id);
        if(bt.isPresent()) {
            return bt.get();
        }
        return null;
    }
    public Teach getTeachById(Long id) {
        if(id == null) {
            return null;
        }
        Optional<Teach> th =  this.teachRepository.findById(id);
        if(th.isPresent()) {
            return th.get();
        }
        return null;
    }

    public List<Department> getDepartmentsByBranchAndAcademicYear(Long branchId, Long academicYearId){
        if(branchId == null || academicYearId == null) {
            Collections.emptyList();
        }
        Department department = new Department();
        Branch branch = this.getBranchById(branchId);
        AcademicYear ay = this.getAcademicYearById(academicYearId);
        department.setBranch(branch);
        department.setAcademicyear(ay);
        Example<Department> example = Example.of(department);
        List<Department> list = this.departmentRepository.findAll(example);
        return list;
    }

    public List<CmsStudentTypeVo> getAllStudentTypes() {
        logger.debug("Retrieving all student types");
        List<CmsStudentTypeVo> ls = new ArrayList<>();
        for(StudentType sm: StudentType.values()) {
            CmsStudentTypeVo vo = new CmsStudentTypeVo();
            vo.setId(sm.value());
            vo.setDescription(sm.getDescription());
            ls.add(vo);
        }
        return ls;
    }

    public CmsStudentTypeVo getStudentType(Long id) {
        StudentType sm = StudentType.valueOf(id.intValue());
        CmsStudentTypeVo vo = new CmsStudentTypeVo();
        vo.setId(sm.value());
        vo.setDescription(sm.getDescription());
        return vo;
    }

    public CmsStudentTypeVo getStudentTypeByDescription(String studentTypeDescription) {
        StudentType sm = StudentType.getStudentTypeOnDescription(studentTypeDescription);
        CmsStudentTypeVo vo = new CmsStudentTypeVo();
        vo.setId(sm.value());
        vo.setDescription(sm.getDescription());
        return vo;
    }

    public List<CmsSemesterVo> getAllSemesters() {
        logger.debug("Retrieving all semesters");
        List<CmsSemesterVo> ls = new ArrayList<>();
        for(Semester sm: Semester.values()) {
            CmsSemesterVo vo = new CmsSemesterVo();
            vo.setId(sm.value());
            vo.setDescription(sm.getDescription());
            ls.add(vo);
        }
        return ls;
    }

    public CmsSemesterVo getSemester(Long id) {
        Semester sm = Semester.valueOf(id.intValue());
        CmsSemesterVo vo = new CmsSemesterVo();
        vo.setId(sm.value());
        vo.setDescription(sm.getDescription());
        return vo;
    }

    public List<Branch> getBranchForCriteria(Long collegeId){
        if(collegeId == null) {
            logger.warn("College id is null. Returning empty branch list.");
            return Collections.emptyList();
        }
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Branch> query = cb.createQuery(Branch.class);
        Root<Branch> root = query.from(Branch.class);
        CriteriaQuery<Branch> select = query.select(root).where(cb.equal(root.get("college"), collegeId));
        TypedQuery<Branch> typedQuery = this.entityManager.createQuery(select);
        List<Branch> branchList = typedQuery.getResultList();
        logger.debug("Returning list of branches from JPA criteria query. Total records : "+branchList.size());
        return branchList;
    }

    public List<Department> getDepartmentForCriteria(List<Branch> branchList, Long academicYearId){
        if(branchList.size() == 0 || academicYearId == null) {
            logger.warn("Either branch list is empty or academic year id is null. Returning empty subject list.");
            logger.warn("Total records in branchList list: "+branchList.size());
            return Collections.emptyList();
        }
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Department> query = cb.createQuery(Department.class);
        Root<Department> root = query.from(Department.class);
        In<Long> inBranch = cb.in(root.get("branch"));
        for (Branch bt : branchList) {
            inBranch.value(bt.getId());
        }
        CriteriaQuery<Department> select = query.select(root).where(cb.and(inBranch), cb.and(cb.equal(root.get("academicyear"), academicYearId)));
        TypedQuery<Department> typedQuery = this.entityManager.createQuery(select);
        List<Department> departmentList = typedQuery.getResultList();
        logger.debug("Returning list of departments from JPA criteria query. Total records : "+departmentList.size());
        return departmentList;
    }

    public List<Batch> getBatchForCriteria(List<Department> dept) {
        if(dept.size() == 0) {
            logger.warn("Department list is empty. Returning empty batch list.");
            return Collections.emptyList();
        }
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Batch> query = cb.createQuery(Batch.class);
        Root<Batch> root = query.from(Batch.class);
        In<Long> inClause = cb.in(root.get("department"));
        for (Department dt : dept) {
            inClause.value(dt.getId());
        }
        CriteriaQuery<Batch> select = query.select(root).where(inClause);
        TypedQuery<Batch> typedQuery = this.entityManager.createQuery(select);
        List<Batch> bth = typedQuery.getResultList();
        logger.debug("Returning list of years (batch) from JPA criteria query. Total records : "+bth.size());
        return bth;
    }

    public List<Subject> getSubjectForCriteria(List<Department> dept, List<Batch> batch){
        if(dept.size() == 0 || batch.size() == 0) {
            logger.warn("Either department or batch list is empty. Returning empty subject list.");
            logger.warn("Total records in department list: "+dept.size()+", total records in batch list: "+batch.size());
            return Collections.emptyList();
        }

        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Subject> query = cb.createQuery(Subject.class);
        Root<Subject> root = query.from(Subject.class);
        In<Long> inDepartment = cb.in(root.get("department"));
        for (Department dt : dept) {
            inDepartment.value(dt.getId());
        }
        In<Long> inBatch = cb.in(root.get("batch"));
        for (Batch bth : batch) {
            inBatch.value(bth.getId());
        }

        CriteriaQuery<Subject> select = query.select(root).where(cb.and(inDepartment), cb.and(inBatch), cb.and(cb.equal(root.get("status"), Status.ACTIVE)));
        TypedQuery<Subject> typedQuery = this.entityManager.createQuery(select);
        List<Subject> subList = typedQuery.getResultList();
        logger.debug("Returning list of subjects from JPA criteria query. Total records : "+subList.size());
        return subList;
    }

    public List<Section> getSectionForCriteria(List<Batch> batch){
        if(batch.size() == 0) {
            logger.warn("Batch list is empty. Returning empty section list.");
            return Collections.emptyList();
        }
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Section> query = cb.createQuery(Section.class);
        Root<Section> root = query.from(Section.class);
        In<Long> inBatch = cb.in(root.get("batch"));
        for (Batch bth : batch) {
            inBatch.value(bth.getId());
        }
        CriteriaQuery<Section> select = query.select(root).where(inBatch);
        TypedQuery<Section> typedQuery = this.entityManager.createQuery(select);
        List<Section> secList = typedQuery.getResultList();
        logger.debug("Returning list of sections from JPA criteria query. Total records : "+secList.size());
        return secList;
    }

    public List<AcademicExamSetting> getExamsForCriteria(List<Department> dept, List<Batch> batch){
        if(dept.size() == 0 || batch.size() == 0 ) {
            logger.warn("Either department or batch list is empty. Returning empty AcademicExamSetting list.");
            logger.warn("Total records in department list: "+dept.size()+", total records in batch list: "+batch.size());
            return Collections.emptyList();
        }

        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<AcademicExamSetting> query = cb.createQuery(AcademicExamSetting.class);
        Root<AcademicExamSetting> root = query.from(AcademicExamSetting.class);
        In<Long> inDepartment = cb.in(root.get("department"));
        for (Department dt : dept) {
            inDepartment.value(dt.getId());
        }
        In<Long> inBatch = cb.in(root.get("batch"));
        for (Batch bth : batch) {
            inBatch.value(bth.getId());
        }
//        In<Long> inSection = cb.in(root.get("section"));
//        for (Section sec : secList) {
//            inSection.value(sec.getId());
//        }
        CriteriaQuery<AcademicExamSetting> select = query.select(root).where(cb.and(inDepartment), cb.and(inBatch));
        TypedQuery<AcademicExamSetting> typedQuery = this.entityManager.createQuery(select);
        List<AcademicExamSetting> examsList = typedQuery.getResultList();
        logger.debug("Returning list of exams from JPA criteria query. Total records : "+examsList.size());
        return examsList;
    }



    public List<Teach> getTeachForCriteria(List<Subject> subjectList, Long teacherId){
        if(subjectList.size() == 0) {
            logger.warn("Subject list is empty. Returning empty teach list.");
            return Collections.emptyList();
        }
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Teach> query = cb.createQuery(Teach.class);
        Root<Teach> root = query.from(Teach.class);
        In<Long> inSubject = cb.in(root.get("subject"));
        for (Subject sub : subjectList) {
            inSubject.value(sub.getId());
        }
        CriteriaQuery<Teach> select = query.select(root).where(cb.and(inSubject), cb.and(cb.equal(root.get("teacher"), teacherId)));
        TypedQuery<Teach> typedQuery = this.entityManager.createQuery(select);
        List<Teach> teachList = typedQuery.getResultList();
        logger.debug("Returning list of teach from JPA criteria query. Total records : "+teachList.size());
        return teachList;
    }

    public List<Teach> getTeachForCriteria(Long teacherId){
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Teach> query = cb.createQuery(Teach.class);
        Root<Teach> root = query.from(Teach.class);
        CriteriaQuery<Teach> select = query.select(root).where(cb.equal(root.get("teacher"), teacherId));
        TypedQuery<Teach> typedQuery = this.entityManager.createQuery(select);
        List<Teach> teachList = typedQuery.getResultList();
        logger.debug("Returning list of teach based on teacher id from JPA criteria query. Total records : "+teachList.size());
        return teachList;
    }

    /**
     * AttendanceMaster for teacher attendance
     * @param batchList
     * @param secList
     * @param teachList
     * @return
     */
    public List<AttendanceMaster> getAttendanceMasterForCriteria(List<Batch> batchList, List<Section> secList, List<Teach> teachList){
        if(batchList.size() == 0 || secList.size() == 0 || teachList.size() == 0) {
            logger.warn("Either batch, section or teach list is empty. Returning empty attendance master list.");
            logger.warn("Total records in batch list: "+batchList.size()+", total records in section list: "+secList.size()	+", total records in teach list: "+teachList.size());
            return Collections.emptyList();
        }
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<AttendanceMaster> query = cb.createQuery(AttendanceMaster.class);
        Root<AttendanceMaster> root = query.from(AttendanceMaster.class);
        In<Long> inBatch = cb.in(root.get("batch"));
        for (Batch bth : batchList) {
            inBatch.value(bth.getId());
        }
        In<Long> inSection = cb.in(root.get("section"));
        for (Section sec : secList) {
            inSection.value(sec.getId());
        }
        In<Long> inTeach = cb.in(root.get("teach"));
        for (Teach tch : teachList) {
            inTeach.value(tch.getId());
        }
        CriteriaQuery<AttendanceMaster> select = query.select(root).where(cb.and(inBatch),cb.and(inSection), cb.and(inTeach));
        TypedQuery<AttendanceMaster> typedQuery = this.entityManager.createQuery(select);
        List<AttendanceMaster> atndMstrList = typedQuery.getResultList();
        logger.debug("Returning list of attendance master from JPA criteria query. Total records : "+atndMstrList.size());
        return atndMstrList;
    }

    /**
     * AttendanceMaster for admin attendance
     * @param batchList
     * @param secList
     * @return
     */
    public List<AttendanceMaster> getAttendanceMasterForCriteria(List<Batch> batchList, List<Section> secList){
        if(batchList.size() == 0 || secList.size() == 0 ) {
            logger.warn("Either batch or section. Returning empty attendance master list.");
            logger.warn("Total records in batch list: "+batchList.size()+" and total records in section list: "+secList.size());
            return Collections.emptyList();
        }
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<AttendanceMaster> query = cb.createQuery(AttendanceMaster.class);
        Root<AttendanceMaster> root = query.from(AttendanceMaster.class);
        In<Long> inBatch = cb.in(root.get("batch"));
        for (Batch bth : batchList) {
            inBatch.value(bth.getId());
        }
        In<Long> inSection = cb.in(root.get("section"));
        for (Section sec : secList) {
            inSection.value(sec.getId());
        }

        CriteriaQuery<AttendanceMaster> select = query.select(root).where(cb.and(inBatch),cb.and(inSection));
        TypedQuery<AttendanceMaster> typedQuery = this.entityManager.createQuery(select);
        List<AttendanceMaster> atndMstrList = typedQuery.getResultList();
        logger.debug("Returning list of attendance master for admin attendance from JPA criteria query. Total records : "+atndMstrList.size());
        return atndMstrList;
    }

    public List<Lecture> getLectureForCriteria(List<AttendanceMaster> atndMstrList, String lectureDate) throws Exception{
        if(atndMstrList.size() == 0) {
            logger.warn("Attendance master list is empty. Returning empty lecture list.");
            return Collections.emptyList();
        }
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Lecture> query = cb.createQuery(Lecture.class);
        Root<Lecture> root = query.from(Lecture.class);
        In<Long> inAtndMstr = cb.in(root.get("attendancemaster"));
        for (AttendanceMaster am : atndMstrList) {
            inAtndMstr.value(am.getId());
        }
//        Date dt = DateFormatUtil.getUtilDate(CmsConstants.DATE_FORMAT_dd_MM_yyyy, lectureDate);
        LocalDate dt = DateFormatUtil.convertStringToLocalDate(lectureDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy);
        
        CriteriaQuery<Lecture> select = query.select(root).where(cb.and(inAtndMstr), cb.and(cb.equal(root.get("lecDate"), dt)));
        TypedQuery<Lecture> typedQuery = this.entityManager.createQuery(select);
        List<Lecture> lectureList = typedQuery.getResultList();
        logger.debug("Returning list of lectures from JPA criteria query. Total records : "+lectureList.size());
        return lectureList;
    }

    public List<Lecture> getLectureForAdminCriteria(List<AttendanceMaster> atndMstrList) throws Exception{
        if(atndMstrList.size() == 0) {
            logger.warn("Attendance master list is empty. Returning empty lecture list.");
            return Collections.emptyList();
        }
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Lecture> query = cb.createQuery(Lecture.class);
        Root<Lecture> root = query.from(Lecture.class);
        In<Long> inAtndMstr = cb.in(root.get("attendancemaster"));
        for (AttendanceMaster am : atndMstrList) {
            inAtndMstr.value(am.getId());
        }
//    	Date dt = DateFormatUtil.getUtilDate(CmsConstants.DATE_FORMAT_dd_MM_yyyy, lectureDate);
        CriteriaQuery<Lecture> select = query.select(root).where(inAtndMstr);
        TypedQuery<Lecture> typedQuery = this.entityManager.createQuery(select);
        List<Lecture> lectureList = typedQuery.getResultList();
        logger.debug("Returning list of lectures from JPA criteria query. Total records : "+lectureList.size());
        return lectureList;
    }

    public List<CmsGenderVo> getAllGenders() {
        logger.debug("Retrieving all genders types");
        List<CmsGenderVo> ls = new ArrayList<>();
        for(Gender sm: Gender.values()) {
            CmsGenderVo vo = new CmsGenderVo();
            vo.setId(sm.value());
            vo.setDescription(sm.getDescription());
            ls.add(vo);
        }
        return ls;
    }

    public CmsGenderVo getGender(Long id) {
        Gender sm = Gender.valueOf(id.intValue());
        CmsGenderVo vo = new CmsGenderVo();
        vo.setId(sm.value());
        vo.setDescription(sm.getDescription());
        return vo;
    }

    public CmsGenderVo getGenderByDescription(String genderDescription) {
        Gender sm = Gender.getGenderOnDescription(genderDescription);
        CmsGenderVo vo = new CmsGenderVo();
        vo.setId(sm.value());
        vo.setDescription(sm.getDescription());
        return vo;
    }

    public List<CmsCourseEnumVo> getAllCourses() {
        logger.debug("Retrieving all courses types");
        List<CmsCourseEnumVo> ls = new ArrayList<>();
        for(Course cr: Course.values()) {
            CmsCourseEnumVo vo = new CmsCourseEnumVo();
            vo.setId(cr.value());
            vo.setDescription(cr.getDescription());
            ls.add(vo);
        }
        return ls;
    }

    public CmsCourseEnumVo getCourse(Long id) {
        Course cr = Course.valueOf(id.intValue());
        CmsCourseEnumVo vo = new CmsCourseEnumVo();
        vo.setId(cr.value());
        vo.setDescription(cr.getDescription());
        return vo;
    }

    public CmsCourseEnumVo getCourseByDescription(String courseDescription) {
        Course cr = Course.getCourseOnDescription(courseDescription);
        CmsCourseEnumVo vo = new CmsCourseEnumVo();
        vo.setId(cr.value());
        vo.setDescription(cr.getDescription());
        return vo;
    }

    public List<CmsTermVo> getTermsByAcademicYear(Long academicYearId) throws Exception{
        if(academicYearId == null) {
            Collections.emptyList();
        }
        Term term = new Term();
        AcademicYear ay = this.getAcademicYearById(academicYearId);
        term.setAcademicyear(ay);
        term.setTermStatus(Status.ACTIVE);
        Example<Term> example = Example.of(term);
        List<Term> list = this.termRepository.findAll(example);
        List<CmsTermVo> ls = new ArrayList<>();
        for(Term tm: list) {

            CmsTermVo ctm = CommonUtil.createCopyProperties(tm, CmsTermVo.class);
            ctm.setStrStartDate(DateFormatUtil.changeLocalDateFormat(tm.getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            ctm.setStrEndDate(DateFormatUtil.changeLocalDateFormat(tm.getEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            tm.setStartDate(null);
            tm.setEndDate(null);
            //            ctm.setStrStartDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(tm.getStartDate()))));
//            ctm.setStrEndDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(tm.getEndDate()))));
            ls.add(ctm);
        }
        return ls;
    }

    public List<CmsFeeCategory> getFeeCategoryForCriteria(List<Branch> branchList) throws ParseException, Exception{
        if(branchList.size() == 0 ) {
            logger.warn("Branch list is empty. Returning empty fee category list.");
            logger.warn("Total records in branchList list: "+branchList.size());
            return Collections.emptyList();
        }
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<FeeCategory> query = cb.createQuery(FeeCategory.class);
        Root<FeeCategory> root = query.from(FeeCategory.class);
        In<Long> inBranch = cb.in(root.get("branch"));
        for (Branch bt : branchList) {
            inBranch.value(bt.getId());
        }
        CriteriaQuery<FeeCategory> select = query.select(root).where(inBranch);
        TypedQuery<FeeCategory> typedQuery = this.entityManager.createQuery(select);
        List<FeeCategory> feeCategoryList = typedQuery.getResultList();
        List<CmsFeeCategory> ls = new ArrayList<>();
        for(FeeCategory ff: feeCategoryList) {
            CmsFeeCategory cfc = CommonUtil.createCopyProperties(ff, CmsFeeCategory.class);
            if(ff.getStartDate() != null) {
            	cfc.setStrStartDate(DateFormatUtil.changeLocalDateFormat(ff.getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	cfc.setStartDate(null);
//                cfc.setStrStartDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, DateFormatUtil.converUtilDateFromLocaDate(ff.getStartDate())));
//        		cfc.setStrStartDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, ff.getStartDate())));
            }
            if(ff.getEndDate() != null) {
            	cfc.setStrEndDate(DateFormatUtil.changeLocalDateFormat(ff.getEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	cfc.setEndDate(null);
//                cfc.setStrEndDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, DateFormatUtil.converUtilDateFromLocaDate(ff.getEndDate())));
//        		ff.setStrEndDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, ff.getEndDate())));
            }
            if(ff.getCreatedOn() != null) {
            	cfc.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(ff.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	cfc.setCreatedOn(null);
            }
            if(ff.getUpdatedOn() != null) {
            	cfc.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(ff.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	cfc.setUpdatedOn(null);
            }
            ls.add(cfc);
        }
        Collections.sort(ls, Collections.reverseOrder());
        logger.debug("Returning list of fee category from JPA criteria query. Total records : "+feeCategoryList.size());
        return ls;
    }

    public List<CmsFeeDetails> getFeeDetailsForCriteria(List<CmsFeeCategory> feeCategoryList) throws ParseException, Exception{
        if(feeCategoryList.size() == 0 ) {
            logger.warn("FeeCategory list is empty. Returning empty fee details list.");
            logger.warn("Total records in branchList list: "+feeCategoryList.size());
            return Collections.emptyList();
        }
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<FeeDetails> query = cb.createQuery(FeeDetails.class);
        Root<FeeDetails> root = query.from(FeeDetails.class);
        In<Long> inFeeCat = cb.in(root.get("feeCategory"));
        for (CmsFeeCategory fc : feeCategoryList) {
            inFeeCat.value(fc.getId());
        }
        CriteriaQuery<FeeDetails> select = query.select(root).where(inFeeCat);
        TypedQuery<FeeDetails> typedQuery = this.entityManager.createQuery(select);
        List<FeeDetails> feeDetailsList = typedQuery.getResultList();
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<CmsFeeDetails> ls = new ArrayList<>();
        for(FeeDetails ff: feeDetailsList) {
            CmsFeeDetails cfd = CommonUtil.createCopyProperties(ff, CmsFeeDetails.class);
            if(ff.getStartDate() != null) {
            	cfd.setStrStartDate(DateFormatUtil.changeLocalDateFormat(ff.getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//                cfd.setStrStartDate(ff.getStartDate().format(dateFormatter));
                cfd.setStartDate(null);
            }
            if(ff.getEndDate() != null) {
//                cfd.setStrEndDate(ff.getEndDate().format(dateFormatter));
            	cfd.setStrEndDate(DateFormatUtil.changeLocalDateFormat(ff.getEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
                cfd.setEndDate(null);
            }
            if(ff.getCreatedOn() != null) {
            	cfd.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(ff.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	cfd.setCreatedOn(null);
            }
            if(ff.getUpdatedOn() != null) {
            	cfd.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(ff.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	cfd.setUpdatedOn(null);
            }
            ls.add(cfd);
        }
        logger.debug("Returning list of fee details from JPA criteria query. Total records : "+feeDetailsList.size());
        return ls;
    }

    public List<CmsFacility> getFacilityForCriteria(List<Branch> branchList, Long academicYearId) throws Exception{
        if(branchList.size() == 0 || academicYearId == null) {
            logger.warn("Either branch list or academic year id is null. Returning empty facility list.");
            logger.warn("Total records in branchList list: "+branchList.size());
            return Collections.emptyList();
        }
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Facility> query = cb.createQuery(Facility.class);
        Root<Facility> root = query.from(Facility.class);
        In<Long> inBranch = cb.in(root.get("branch"));
        for (Branch bt : branchList) {
            inBranch.value(bt.getId());
        }
        CriteriaQuery<Facility> select = query.select(root).where(cb.and(inBranch), cb.and(cb.equal(root.get("academicYear"), academicYearId)));
        TypedQuery<Facility> typedQuery = this.entityManager.createQuery(select);
        List<Facility> facilityList = typedQuery.getResultList();
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<CmsFacility> ls = new ArrayList<>();
        for(Facility ff: facilityList) {
            CmsFacility cf = CommonUtil.createCopyProperties(ff, CmsFacility.class);
            if(ff.getStartDate() != null) {
            	cf.setStrStartDate(DateFormatUtil.changeLocalDateFormat(ff.getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//                cf.setStrStartDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ff.getStartDate()))));
            	cf.setStartDate(null);
            }
            if(ff.getEndDate() != null) {
            	cf.setStrEndDate(DateFormatUtil.changeLocalDateFormat(ff.getEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	cf.setEndDate(null);
//                cf.setStrEndDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ff.getEndDate()))));
            }
            if(ff.getSuspandStartDate() != null) {
            	cf.setStrSuspandStartDate(DateFormatUtil.changeLocalDateFormat(ff.getSuspandStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	cf.setSuspandStartDate(null);
//                cf.setStrSuspandStartDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ff.getSuspandStartDate()))));
            }
            if(ff.getSuspandEndDate() != null) {
            	cf.setStrSuspandEndDate(DateFormatUtil.changeLocalDateFormat(ff.getSuspandEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            	cf.setSuspandEndDate(null);
//                cf.setStrSuspandEndDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ff.getSuspandEndDate()))));
            }
            ls.add(cf);
        }
        logger.debug("Returning list of facilities from JPA criteria query. Total records : "+facilityList.size());
        return ls;
    }
    
    public Config createUserConfig(String userName) {
		logger.debug("Creating user specific config object");
		Config config = new Config();
		config.setLoggedInUser(userName);
		
        Student st = new Student();
        Teacher th = new Teacher();
        Employee em = new Employee();
        st.setStudentEmailAddress(userName);
        th.setTeacherEmailAddress(userName);
        em.setOfficialMailId(userName);
        Optional<Student> student = studentRepository.findOne(Example.of(st));
        Optional<Teacher> teacher = teacherRepository.findOne(Example.of(th));
        Optional<Employee> employee = employeeRepository.findOne(Example.of(em));
        if(student.isPresent()) {
        	config.setLoggedInUser(userName);
        	config.setCountry(student.get().getBranch().getState().getCountry());
        	config.setState(student.get().getBranch().getState());
        	config.setCity(student.get().getBranch().getCity());
        	config.setBranch(student.get().getBranch());
        }else if(teacher.isPresent()) {
        	config.setLoggedInUser(userName);
        	config.setCountry(teacher.get().getBranch().getState().getCountry());
        	config.setState(teacher.get().getBranch().getState());
        	config.setCity(teacher.get().getBranch().getCity());
        	config.setBranch(teacher.get().getBranch());
        }else if(employee.isPresent()) {
        	config.setLoggedInUser(userName);
        	config.setCountry(employee.get().getBranch().getState().getCountry());
        	config.setState(teacher.get().getBranch().getState());
        	config.setCity(teacher.get().getBranch().getCity());
        	config.setBranch(employee.get().getBranch());
        }
        
//        if(config.getCollege() == null) {
//	        List<College> cl = collegeRepository.findAll();
//	        config.setCollege((cl != null && cl.size() > 1) ? cl.get(0) : null);
//        }
        config.setCollege(GlobalConfig.CONFIG.getCollege());
        AcademicYear academicYear = new AcademicYear();
        academicYear.setStatus(Status.ACTIVE);
        Optional<AcademicYear> oa = academicYearRepository.findOne(Example.of(academicYear));
        if(oa.isPresent()) {
        	CmsAcademicYearVo vo = CommonUtil.createCopyProperties(oa.get(), CmsAcademicYearVo.class);
        	vo.setStrStartDate(DateFormatUtil.changeLocalDateFormat(oa.get().getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        	vo.setStrEndDate(DateFormatUtil.changeLocalDateFormat(oa.get().getEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        	config.setCmsAcademicYearVo(vo);
        }else {
        	config.setCmsAcademicYearVo(new CmsAcademicYearVo());
        }
        config.setBranchList(this.branchRepository.findAll());
        
        return config;
	}
    
    public Config createUserConfigForAdmin(String userName) {
		logger.debug("Creating admin user specific config object");
		Config config = new Config();
		config.setLoggedInUser(userName);
		config.setCollege(GlobalConfig.CONFIG.getCollege());
        AcademicYear academicYear = new AcademicYear();
        academicYear.setStatus(Status.ACTIVE);
        Optional<AcademicYear> oa = academicYearRepository.findOne(Example.of(academicYear));
        if(oa.isPresent()) {
        	CmsAcademicYearVo vo = CommonUtil.createCopyProperties(oa.get(), CmsAcademicYearVo.class);
        	vo.setStrStartDate(DateFormatUtil.changeLocalDateFormat(oa.get().getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        	vo.setStrEndDate(DateFormatUtil.changeLocalDateFormat(oa.get().getEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        	config.setCmsAcademicYearVo(vo);
        }else {
        	config.setCmsAcademicYearVo(new CmsAcademicYearVo());
        }
        config.setBranchList(this.branchRepository.findAll());
        
        return config;
	}
    
    public List<CmsNotificationsVo> getNotifications(){
    	logger.debug("Getting notifications data");
    	AcademicYear ay = getActiveAcademicYear();
    	Notifications ntf = new Notifications();
    	ntf.setAcademicYear(ay);
    	List<Notifications> list = this.notificationsRepository.findAll(Example.of(ntf));
    	List<CmsNotificationsVo> ls = new ArrayList<>();
    	for(Notifications n: list) {
    		CmsNotificationsVo vo = CommonUtil.createCopyProperties(n, CmsNotificationsVo.class);
    		if(n.getCreatedOn() != null) {
    			vo.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(n.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    		}
    		if(n.getUpdatedOn() != null) {
    			vo.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(n.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
    		}
    		logger.debug("Notifications : "+vo);
    		ls.add(vo);
    	}
    	return ls;
    }

    public List<Student> getAllStudentsOfCurrentAcademicYear(){
		AcademicYear ay = getActiveAcademicYear();
		Department department = new Department();
		department.setAcademicyear(ay);
		Student student = new Student();
		student.setDepartment(department);
		List<Student> list = this.studentRepository.findAll(Example.of(student));
		return list;
	}
    
    public List<Subject>getAllSubjectsOfStudent(Student student){
		Subject subject = new Subject();
		subject.setDepartment(student.getDepartment());
		subject.setBatch(student.getBatch());
		List<Subject> list = this.subjectRepository.findAll(Example.of(subject));
		return list;
	}
    
    public long getTotalLecturesScheduledForStudent(Student student) {
		StudentAttendance sa = new StudentAttendance();
		sa.setStudent(student);
		long count = this.studentAttendanceRepository.count(Example.of(sa));
		logger.debug("Total lectures scheduled for student : "+student.getStudentEmailAddress()+" are : "+count);
		return count;
	}
    
    public List<StudentAttendance> getTotalLecturesConductedForStudent(Student student, LocalDate dt) throws Exception{
        
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<StudentAttendance> query = cb.createQuery(StudentAttendance.class);
        Root<StudentAttendance> root = query.from(StudentAttendance.class);
        List<Lecture> lecList = getLectures(dt);
        
        In<Long> inLecture = cb.in(root.get("lecture"));
        for (Lecture lec : lecList) {
        	inLecture.value(lec.getId());
        }
        
        CriteriaQuery<StudentAttendance> select = query.select(root).where(cb.and(inLecture), cb.and(cb.equal(root.get("student"), student.getId())));
        TypedQuery<StudentAttendance> typedQuery = this.entityManager.createQuery(select);
        List<StudentAttendance> lectureList = typedQuery.getResultList();
        logger.debug("Lecture date : "+dt+". Student id : "+student.getId()+". Student email : "+student.getStudentEmailAddress()+". Total lectures conducted till date for given : "+lectureList.size());
        return lectureList;
    }
    
    public List<Lecture> getLectures(LocalDate dt){
    	CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Lecture> query = cb.createQuery(Lecture.class);
        Root<Lecture> root = query.from(Lecture.class);
        
        CriteriaQuery<Lecture> select = query.select(root).where(cb.lessThanOrEqualTo(root.get("lecDate"), dt));
        TypedQuery<Lecture> typedQuery = this.entityManager.createQuery(select);
        List<Lecture> lectureList = typedQuery.getResultList();
        logger.debug("Lecture date : "+dt+". Total lectures conducted till date : "+lectureList.size());
        return lectureList;
    }
    
    public List<Teacher> getAllActiveTeachers(){
    	Teacher teacher = new Teacher();
    	teacher.setStatus(Status.ACTIVE);
    	List<Teacher> list = this.teacherRepository.findAll(Example.of(teacher));
    	logger.debug("Total teachers irrespective of branches : "+list.size());
    	return list;
    }
    
    public List<Teach> getAllSubjectsOfTeacher(Teacher th){
    	Teach teach = new Teach();
    	teach.setTeacher(th);
    	List<Teach> list = this.teachRepository.findAll(Example.of(teach));
    	logger.debug("Total subjects teach by teacher "+th.getTeacherName() + " are : "+list.size());
    	return list;
    }
    
    public long getTotalLecturesScheduledForTeacher(Teacher th) {
		StudentAttendance sa = new StudentAttendance();
//		sa.setStudent(student);
//		long count = this.studentAttendanceRepository.count(Example.of(sa));
//		logger.debug("Total lectures scheduled for student : "+student.getStudentEmailAddress()+" are : "+count);
		return 0;
	}
//    public static void main(String a[]) {
//        LocalDate ld = LocalDate.now();
//        System.out.println(ld);
//
//        String strLd =  ld.format(DateTimeFormatter.ofPattern("d-MM-yyyy"));
//        LocalDate localDate = LocalDate.parse(strLd, DateTimeFormatter.ofPattern("d-MM-yyyy"));
//        System.out.println(localDate);
//    }
} 
