export interface IDepartment {
  id?: number;
  name?: string;
  description?: string;
  deptHead?: string;
  branchId?: number;
  academicyearId?: number;
}

export const defaultValue: Readonly<IDepartment> = {};
