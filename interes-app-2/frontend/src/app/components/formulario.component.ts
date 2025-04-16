import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { InteresRequest } from '../models/interes-request.model';
import { InteresResponse } from '../models/interes-response.model';
import { InteresService } from '../services/interes.service';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit {
  form: FormGroup;
  resultado?: InteresResponse;
  historial: any[] = [];

  constructor(private fb: FormBuilder, private interesService: InteresService) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      montoInicial: [null, [Validators.required, Validators.min(1)]],
      tasaInteres: [null, [Validators.required, Validators.min(1)]],
      anios: [null, [Validators.required, Validators.min(1), Validators.max(50)]]
    });

    this.interesService.obtenerHistorial().subscribe(data => {
      this.historial = data;
    });
  }

  enviar(): void {
    if (this.form.valid) {
      const req: InteresRequest = this.form.value;
      this.interesService.calcularInteres(req).subscribe({
        next: (data) => {
          this.resultado = data;
          this.historial.push({
            id: data.id,
            fecha: data.fecha,
            montoInicial: req.montoInicial,
            tasaInteres: req.tasaInteres,
            anios: req.anios
          });
        },
        error: (err) => {
          alert(err.error?.error || 'Ocurrió un error en el cálculo');
        }
      });
    }
  }
}