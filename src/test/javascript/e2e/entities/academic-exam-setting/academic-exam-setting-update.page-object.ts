import { element, by, ElementFinder } from 'protractor';

export default class AcademicExamSettingUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.academicExamSetting.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  examTypeInput: ElementFinder = element(by.css('input#academic-exam-setting-examType'));
  subjectInput: ElementFinder = element(by.css('input#academic-exam-setting-subject'));
  dateInput: ElementFinder = element(by.css('input#academic-exam-setting-date'));
  dayInput: ElementFinder = element(by.css('input#academic-exam-setting-day'));
  durationInput: ElementFinder = element(by.css('input#academic-exam-setting-duration'));
  startTimeInput: ElementFinder = element(by.css('input#academic-exam-setting-startTime'));
  endTimeInput: ElementFinder = element(by.css('input#academic-exam-setting-endTime'));
  totalInput: ElementFinder = element(by.css('input#academic-exam-setting-total'));
  passingInput: ElementFinder = element(by.css('input#academic-exam-setting-passing'));
  actionsInput: ElementFinder = element(by.css('input#academic-exam-setting-actions'));
  departmentSelect: ElementFinder = element(by.css('select#academic-exam-setting-department'));
  academicYearSelect: ElementFinder = element(by.css('select#academic-exam-setting-academicYear'));
  attendanceMasterSelect: ElementFinder = element(by.css('select#academic-exam-setting-attendanceMaster'));
  sectionSelect: ElementFinder = element(by.css('select#academic-exam-setting-section'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setExamTypeInput(examType) {
    await this.examTypeInput.sendKeys(examType);
  }

  async getExamTypeInput() {
    return this.examTypeInput.getAttribute('value');
  }

  async setSubjectInput(subject) {
    await this.subjectInput.sendKeys(subject);
  }

  async getSubjectInput() {
    return this.subjectInput.getAttribute('value');
  }

  async setDateInput(date) {
    await this.dateInput.sendKeys(date);
  }

  async getDateInput() {
    return this.dateInput.getAttribute('value');
  }

  async setDayInput(day) {
    await this.dayInput.sendKeys(day);
  }

  async getDayInput() {
    return this.dayInput.getAttribute('value');
  }

  async setDurationInput(duration) {
    await this.durationInput.sendKeys(duration);
  }

  async getDurationInput() {
    return this.durationInput.getAttribute('value');
  }

  async setStartTimeInput(startTime) {
    await this.startTimeInput.sendKeys(startTime);
  }

  async getStartTimeInput() {
    return this.startTimeInput.getAttribute('value');
  }

  async setEndTimeInput(endTime) {
    await this.endTimeInput.sendKeys(endTime);
  }

  async getEndTimeInput() {
    return this.endTimeInput.getAttribute('value');
  }

  async setTotalInput(total) {
    await this.totalInput.sendKeys(total);
  }

  async getTotalInput() {
    return this.totalInput.getAttribute('value');
  }

  async setPassingInput(passing) {
    await this.passingInput.sendKeys(passing);
  }

  async getPassingInput() {
    return this.passingInput.getAttribute('value');
  }

  async setActionsInput(actions) {
    await this.actionsInput.sendKeys(actions);
  }

  async getActionsInput() {
    return this.actionsInput.getAttribute('value');
  }

  async departmentSelectLastOption() {
    await this.departmentSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async departmentSelectOption(option) {
    await this.departmentSelect.sendKeys(option);
  }

  getDepartmentSelect() {
    return this.departmentSelect;
  }

  async getDepartmentSelectedOption() {
    return this.departmentSelect.element(by.css('option:checked')).getText();
  }

  async academicYearSelectLastOption() {
    await this.academicYearSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async academicYearSelectOption(option) {
    await this.academicYearSelect.sendKeys(option);
  }

  getAcademicYearSelect() {
    return this.academicYearSelect;
  }

  async getAcademicYearSelectedOption() {
    return this.academicYearSelect.element(by.css('option:checked')).getText();
  }

  async attendanceMasterSelectLastOption() {
    await this.attendanceMasterSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async attendanceMasterSelectOption(option) {
    await this.attendanceMasterSelect.sendKeys(option);
  }

  getAttendanceMasterSelect() {
    return this.attendanceMasterSelect;
  }

  async getAttendanceMasterSelectedOption() {
    return this.attendanceMasterSelect.element(by.css('option:checked')).getText();
  }

  async sectionSelectLastOption() {
    await this.sectionSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async sectionSelectOption(option) {
    await this.sectionSelect.sendKeys(option);
  }

  getSectionSelect() {
    return this.sectionSelect;
  }

  async getSectionSelectedOption() {
    return this.sectionSelect.element(by.css('option:checked')).getText();
  }

  async save() {
    await this.saveButton.click();
  }

  async cancel() {
    await this.cancelButton.click();
  }

  getSaveButton() {
    return this.saveButton;
  }
}
