import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pessoa } from '../model/pessoa.module';

const url = 'http://localhost:8080/pessoa';
// const httpOptions = {
//   headers: new HttpHeaders({'content-Type': 'aplication/json'})
// };

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  constructor(private http: HttpClient) { }

  consultar (): Observable<Pessoa[]> {
    return this.http.get<Pessoa[]>(url);
  }

  consultarPorId (id: number): Observable<Pessoa> {
    const urlLocal = `${url}/${id}`;
    return this.http.get<Pessoa>(urlLocal);
  }

  adicionar (Pessoa): Observable<Pessoa> {
    return this.http.post<Pessoa>(url, Pessoa);
  }

  alterar (id, Pessoa): Observable<any> {
    const urlLocal = `${url}/${id}`;
    return this.http.put<Pessoa>(urlLocal, Pessoa);
  }

  excluir (id): Observable<Pessoa> {
    const urlLocal = `${url}/${id}`;
    return this.http.delete<Pessoa>(urlLocal);
  }

}