import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html'
})
export class LoginComponent {
  username = '';
  password = '';
  error    = '';
  loading  = false;

  constructor(private auth: AuthService, private router: Router) {}

  login() {
    this.loading = true;
    this.error   = '';
    this.auth.login({ username: this.username, password: this.password }).subscribe({
      next: () => this.router.navigate(['/clients']),
      error: () => {
        this.error   = 'Identifiants invalides.';
        this.loading = false;
      }
    });
  }
}
