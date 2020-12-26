export interface Report {
  id: string;
  description: string;
  reportStatus: ReportStatus;
  city: string;
  street: string;
  streetNumber: number;
  longitude: number;
  latitude: number;
  accountId: string;
  role: Role;
  createdBy?: string;
  createdDateTime: string;
  updatedBy?: string;
  updatedDateTime?: string;
}

export enum ReportStatus {
  NEW = 'NEW',
  VERIFIED = 'VERIFIED',
  RESOLVED = 'RESOLVED',
}

export enum Role {
  USER = "USER",
  ADMIN = "ADMIN",
}
