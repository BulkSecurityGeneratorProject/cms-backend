import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAcademicYear } from 'app/shared/model/academic-year.model';
import { getEntities as getAcademicYears } from 'app/entities/academic-year/academic-year.reducer';
import { getEntity, updateEntity, createEntity, reset } from './holiday.reducer';
import { IHoliday } from 'app/shared/model/holiday.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IHolidayUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IHolidayUpdateState {
  isNew: boolean;
  academicyearId: number;
}

export class HolidayUpdate extends React.Component<IHolidayUpdateProps, IHolidayUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      academicyearId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getAcademicYears();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { holidayEntity } = this.props;
      const entity = {
        ...holidayEntity,
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
    this.props.history.push('/entity/holiday');
  };

  render() {
    const { holidayEntity, academicYears, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.holiday.home.createOrEditLabel">
              <Translate contentKey="cmsApp.holiday.home.createOrEditLabel">Create or edit a Holiday</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : holidayEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="holiday-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="holidayDescLabel" for="holidayDesc">
                    <Translate contentKey="cmsApp.holiday.holidayDesc">Holiday Desc</Translate>
                  </Label>
                  <AvField
                    id="holiday-holidayDesc"
                    type="text"
                    name="holidayDesc"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="holidayDateLabel" for="holidayDate">
                    <Translate contentKey="cmsApp.holiday.holidayDate">Holiday Date</Translate>
                  </Label>
                  <AvField
                    id="holiday-holidayDate"
                    type="date"
                    className="form-control"
                    name="holidayDate"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="holidayStatusLabel">
                    <Translate contentKey="cmsApp.holiday.holidayStatus">Holiday Status</Translate>
                  </Label>
                  <AvInput
                    id="holiday-holidayStatus"
                    type="select"
                    className="form-control"
                    name="holidayStatus"
                    value={(!isNew && holidayEntity.holidayStatus) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">
                      <Translate contentKey="cmsApp.Status.ACTIVE" />
                    </option>
                    <option value="DEACTIVE">
                      <Translate contentKey="cmsApp.Status.DEACTIVE" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="academicyear.id">
                    <Translate contentKey="cmsApp.holiday.academicyear">Academicyear</Translate>
                  </Label>
                  <AvInput id="holiday-academicyear" type="select" className="form-control" name="academicyearId">
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
                <Button tag={Link} id="cancel-save" to="/entity/holiday" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
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
  academicYears: storeState.academicYear.entities,
  holidayEntity: storeState.holiday.entity,
  loading: storeState.holiday.loading,
  updating: storeState.holiday.updating
});

const mapDispatchToProps = {
  getAcademicYears,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HolidayUpdate);