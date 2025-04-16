export interface MontoAnual {
  anio: number;
  monto: number;
}

export interface InteresResponse {
  id: number;
  fecha: string;
  interes_compuesto: MontoAnual[];
  interes_simple: MontoAnual[];
}