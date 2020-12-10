export interface Measurement {
  current: MeasurementEntry;
  history: Array<MeasurementEntry>;
}

export interface MeasurementEntry {
  fromDateTime: string;
  tillDateTime: string;
  values: Array<MeasurementValue>;
  indexes: Array<MeasurementIndex>;
  standards: Array<Standard>;
}

export interface MeasurementValue {
  name: string;
  value: number;
}

export interface MeasurementIndex {
  name: string;
  value: number;
  level: string;
  description: string;
  advice: string;
  color: string;
}

export interface Standard {
  name: string;
  pollutant: string;
  limit: number;
  percent: number;
  averaging: number;
}
