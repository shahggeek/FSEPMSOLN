import { TestBed } from '@angular/core/testing';

import { ProjectRestService } from './project-rest.service';

describe('ProjectRestService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProjectRestService = TestBed.get(ProjectRestService);
    expect(service).toBeTruthy();
  });
});
