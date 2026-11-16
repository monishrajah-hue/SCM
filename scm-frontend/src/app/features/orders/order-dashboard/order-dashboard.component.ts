// src/app/features/orders/order-dashboard/order-dashboard.component.ts
import { Component, ChangeDetectionStrategy, signal, inject, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { OrderService } from '../../../core/services/order.service';
import { Subscription } from 'rxjs';

// Angular Component - UI building block; @Input and @Output - component communication
@Component({
  selector: 'app-order-dashboard',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './order-dashboard.component.html',
  // Angular Performance Optimization - Change Detection, OnPush
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class OrderDashboardComponent implements OnInit, OnDestroy {
  private fb = inject(FormBuilder);
  private orderService = inject(OrderService);
  private sub = new Subscription(); // Subscription Cleanup

  orderForm!: FormGroup;
  
  // Signals - reactive state; Deferrable Views (@defer) - lazy UI chunks
  orders = signal<{ id: string, status: string }[]>([]);
  isLoading = signal<boolean>(false);

  ngOnInit(): void {
    this.orderForm = this.fb.group({
      orderId: ['', [Validators.required, Validators.minLength(3)]], // Custom Validators conceptually applied
      expedite: [false]
    });
  }

  submitOrder(): void {
    if (this.orderForm.valid) {
      this.isLoading.set(true);
      const { orderId, expedite } = this.orderForm.value;
      
      this.sub.add(
        this.orderService.createOrder(orderId, expedite).subscribe({
          next: () => {
            this.orders.update(currentOrders => [...currentOrders, { id: orderId, status: 'CREATED' }]);
            this.isLoading.set(false);
            this.orderForm.reset();
          },
          error: () => this.isLoading.set(false)
        })
      );
    }
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }
}