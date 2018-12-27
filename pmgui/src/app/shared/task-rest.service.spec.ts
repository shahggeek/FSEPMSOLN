import { TestBed } from '@angular/core/testing';

import { TaskRestService } from './task-rest.service';

describe('TaskRestService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TaskRestService = TestBed.get(TaskRestService);
    expect(service).toBeTruthy();
  });
});
