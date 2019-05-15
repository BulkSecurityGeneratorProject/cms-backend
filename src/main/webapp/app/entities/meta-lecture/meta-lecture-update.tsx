import * as React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IBranch } from 'app/shared/model/branch.model';
import { getEntities as getBranches } from 'app/entities/branch/branch.reducer';
import { IDepartment } from 'app/shared/model/department.model';
import { getEntities as getDepartments } from 'app/entities/department/department.reducer';
import { ISubject } from 'app/shared/model/subject.model';
import { getEntities as getSubjects } from 'app/entities/subject/subject.reducer';
import { ITeacher } from 'app/shared/model/teacher.model';
import { getEntities as getTeachers } from 'app/entities/teacher/teacher.reducer';
import { ITerm } from 'app/shared/model/term.model';
import { getEntities as getTerms } from 'app/entities/term/term.reducer';
import { IAcademicYear } from 'app/shared/model/academic-year.model';
import { getEntities as getAcademicYears } from 'app/entities/academic-year/academic-year.reducer';
import { ISection } from 'app/shared/model/section.model';
import { getEntities as getSections } from 'app/entities/section/section.reducer';
import { IBatch } from 'app/shared/model/batch.model';
import { getEntities as getBatches } from 'app/entities/batch/batch.reducer';
import { getEntity, updateEntity, createEntity, reset } from './meta-lecture.reducer';
import { IMetaLecture } from 'app/shared/model/meta-lecture.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IMetaLectureUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IMetaLectureUpdateState {
  isNew: boolean;
  branchId: number;
  departmentId: number;
  subjectId: number;
  teacherId: number;
  termId: number;
  academicyearId: number;
  sectionId: number;
  batchId: number;
}

export class MetaLectureUpdate extends React.Component<IMetaLectureUpdateProps, IMetaLectureUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      branchId: 0,
      departmentId: 0,
      subjectId: 0,
      teacherId: 0,
      termId: 0,
      academicyearId: 0,
      sectionId: 0,
      batchId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getBranches();
    this.props.getDepartments();
    this.props.getSubjects();
    this.props.getTeachers();
    this.props.getTerms();
    this.props.getAcademicYears();
    this.props.getSections();
    this.props.getBatches();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { metaLectureEntity } = this.props;
      const entity = {
        ...metaLectureEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
      this.handleClose();
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/meta-lecture');
  };

  branchUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        branchId: -1
      });
    } else {
      for (const i in this.props.branches) {
        if (id === this.props.branches[i].id.toString()) {
          this.setState({
            branchId: this.props.branches[i].id
          });
        }
      }
    }
  };

  departmentUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        departmentId: -1
      });
    } else {
      for (const i in this.props.departments) {
        if (id === this.props.departments[i].id.toString()) {
          this.setState({
            departmentId: this.props.departments[i].id
          });
        }
      }
    }
  };

  subjectUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        subjectId: -1
      });
    } else {
      for (const i in this.props.subjects) {
        if (id === this.props.subjects[i].id.toString()) {
          this.setState({
            subjectId: this.props.subjects[i].id
          });
        }
      }
    }
  };

  teacherUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        teacherId: -1
      });
    } else {
      for (const i in this.props.teachers) {
        if (id === this.props.teachers[i].id.toString()) {
          this.setState({
            teacherId: this.props.teachers[i].id
          });
        }
      }
    }
  };

  termUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        termId: -1
      });
    } else {
      for (const i in this.props.terms) {
        if (id === this.props.terms[i].id.toString()) {
          this.setState({
            termId: this.props.terms[i].id
          });
        }
      }
    }
  };

  academicyearUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        academicyearId: -1
      });
    } else {
      for (const i in this.props.academicYears) {
        if (id === this.props.academicYears[i].id.toString()) {
          this.setState({
            academicyearId: this.props.academicYears[i].id
          });
        }
      }
    }
  };

  sectionUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        sectionId: -1
      });
    } else {
      for (const i in this.props.sections) {
        if (id === this.props.sections[i].id.toString()) {
          this.setState({
            sectionId: this.props.sections[i].id
          });
        }
      }
    }
  };

  batchUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        batchId: -1
      });
    } else {
      for (const i in this.props.batches) {
        if (id === this.props.batches[i].id.toString()) {
          this.setState({
            batchId: this.props.batches[i].id
          });
        }
      }
    }
  };

  render() {
    const isInvalid = false;
    const {
      metaLectureEntity,
      branches,
      departments,
      subjects,
      teachers,
      terms,
      academicYears,
      sections,
      batches,
      loading,
      updating
    } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.metaLecture.home.createOrEditLabel">Create or edit a MetaLecture</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : metaLectureEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="meta-lecture-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="weekDayLabel" for="weekDay">
                    Week Day
                  </Label>
                  <AvField id="meta-lecture-weekDay" type="text" name="weekDay" />
                </AvGroup>
                <AvGroup>
                  <Label id="startTimeLabel" for="startTime">
                    Start Time
                  </Label>
                  <AvField id="meta-lecture-startTime" type="text" name="startTime" />
                </AvGroup>
                <AvGroup>
                  <Label id="endTimeLabel" for="endTime">
                    End Time
                  </Label>
                  <AvField id="meta-lecture-endTime" type="text" name="endTime" />
                </AvGroup>
                <AvGroup>
                  <Label for="branch.id">Branch</Label>
                  <AvInput id="meta-lecture-branch" type="select" className="form-control" name="branchId" onChange={this.branchUpdate}>
                    <option value="" key="0" />
                    {branches
                      ? branches.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="department.id">Department</Label>
                  <AvInput
                    id="meta-lecture-department"
                    type="select"
                    className="form-control"
                    name="departmentId"
                    onChange={this.departmentUpdate}
                  >
                    <option value="" key="0" />
                    {departments
                      ? departments.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="subject.id">Subject</Label>
                  <AvInput id="meta-lecture-subject" type="select" className="form-control" name="subjectId" onChange={this.subjectUpdate}>
                    <option value="" key="0" />
                    {subjects
                      ? subjects.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="teacher.id">Teacher</Label>
                  <AvInput id="meta-lecture-teacher" type="select" className="form-control" name="teacherId" onChange={this.teacherUpdate}>
                    <option value="" key="0" />
                    {teachers
                      ? teachers.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="term.id">Term</Label>
                  <AvInput id="meta-lecture-term" type="select" className="form-control" name="termId" onChange={this.termUpdate}>
                    <option value="" key="0" />
                    {terms
                      ? terms.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="academicyear.id">Academicyear</Label>
                  <AvInput
                    id="meta-lecture-academicyear"
                    type="select"
                    className="form-control"
                    name="academicyearId"
                    onChange={this.academicyearUpdate}
                  >
                    <option value="" key="0" />
                    {academicYears
                      ? academicYears.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="section.id">Section</Label>
                  <AvInput id="meta-lecture-section" type="select" className="form-control" name="sectionId" onChange={this.sectionUpdate}>
                    <option value="" key="0" />
                    {sections
                      ? sections.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="batch.id">Batch</Label>
                  <AvInput id="meta-lecture-batch" type="select" className="form-control" name="batchId" onChange={this.batchUpdate}>
                    <option value="" key="0" />
                    {batches
                      ? batches.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/meta-lecture" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={isInvalid || updating}>
                  <FontAwesomeIcon icon="save" />&nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  branches: storeState.branch.entities,
  departments: storeState.department.entities,
  subjects: storeState.subject.entities,
  teachers: storeState.teacher.entities,
  terms: storeState.term.entities,
  academicYears: storeState.academicYear.entities,
  sections: storeState.section.entities,
  batches: storeState.batch.entities,
  metaLectureEntity: storeState.metaLecture.entity,
  loading: storeState.metaLecture.loading,
  updating: storeState.metaLecture.updating
});

const mapDispatchToProps = {
  getBranches,
  getDepartments,
  getSubjects,
  getTeachers,
  getTerms,
  getAcademicYears,
  getSections,
  getBatches,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MetaLectureUpdate);