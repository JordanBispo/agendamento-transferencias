import { Component, OnInit } from '@angular/core';
import { TransferenciaService, Transferencia } from '../transferencia.service';

@Component({
  selector: 'app-transferencias',
  templateUrl: './transferencias.component.html'
})
export class TransferenciasComponent implements OnInit {
  lista: Transferencia[] = [];
  form: Transferencia = {
    contaOrigem: '',
    contaDestino: '',
    valor: 0,
    dataTransferencia: ''
  };

  constructor(private service: TransferenciaService) {}

  ngOnInit() {
    this.carregar();
  }

  carregar() {
    this.service.listar().subscribe(dados => this.lista = dados);
  }

  criar() {
    this.service.agendar(this.form).subscribe({
      next: () => {
        this.carregar();
        this.form = { contaOrigem: '', contaDestino: '', valor: 0, dataTransferencia: '' };
      },
      error: () => alert('Erro ao agendar transferência')
    });
  }
}