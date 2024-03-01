import {Component, OnDestroy, OnInit} from '@angular/core';
import {RandomNumberEntry} from "./random-number-entry";
import {ExampleResponseDto, ExampleService} from "../../api";
import {delay, interval, Subscription, tap, timer} from "rxjs";

const TIMER_INTERVAL_IN_SECONDS: number = 5;

@Component({
  selector: 'app-random-number',
  templateUrl: './random-number.component.html'
})
export class RandomNumberComponent implements OnInit, OnDestroy{

  private autoFetchSubscription: Subscription | undefined;
  fetchDisabled: boolean = false;
  results: RandomNumberEntry[] = [];
  remainingSeconds: number = TIMER_INTERVAL_IN_SECONDS;

  constructor(private readonly exampleService: ExampleService) {
  }

  ngOnInit(): void {
    this.autoFetchSubscription = interval(1000).subscribe(() => this.tick())
  }

  ngOnDestroy(): void {
    this.autoFetchSubscription?.unsubscribe();
  }

  fetchRandomNumber(): void {
    if(this.fetchDisabled) {
      return;
    }

    this.exampleService.getRandomNumber()
      .pipe(
        tap({
          subscribe: () => this.fetchDisabled = true
        }),
        delay(250),
        tap({
          finalize: () => this.fetchDisabled = false,
          next: (value: ExampleResponseDto) => this.pushNewResult(value)
        }))
      .subscribe()
  }

  clearResults(): void {
    this.results = [];
  }

  getProgressValue(): number {
    return ((TIMER_INTERVAL_IN_SECONDS - this.remainingSeconds)/ TIMER_INTERVAL_IN_SECONDS) * 100;
  }

  private pushNewResult(value: ExampleResponseDto): void {
    this.results.splice(0, 0, {
      value: value.randomNumber,
      timestamp: new Date()
    });
  }

  private tick(): void {
    if(this.remainingSeconds === 0) {
      this.remainingSeconds = TIMER_INTERVAL_IN_SECONDS;
      this.fetchRandomNumber();
      return;
    }
    this.remainingSeconds--;
  }

}
