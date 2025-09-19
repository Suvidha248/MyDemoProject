import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule  } from '@angular/forms';
import { AuthService } from '../../app/services/auth.service';  // adjust path to your service
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule,
    HttpClientModule
    ]
})
export class LoginComponent {
  loginForm: FormGroup;
  hidePassword = true;
  error: string | null = null;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  togglePasswordVisibility(): void {
    this.hidePassword = !this.hidePassword;
  }

  login() {
    if (this.loginForm.valid) {
      this.error = null;
      this.loading = true;
      const { email, password } = this.loginForm.value;
      this.authService.login(email, password).subscribe({
        next: (res) => {
          console.log('Login success, token:', res.token);
          localStorage.setItem('auth_token', res.token); // store token
          this.router.navigate(['/home']); // navigate to a secure page
          this.loading = false;
        },
        error: (err) => {
          console.error('Login failed', err);
          this.error = 'Invalid username or password';
          this.loading = false;
        }
      });
    }
  }
}
