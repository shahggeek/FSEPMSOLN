import { Directive, Input, OnInit, ElementRef, Renderer } from '@angular/core';

@Directive({selector: '[sortColumn]'})

export class SortDirective implements OnInit {
  @Input() data: any[];
  @Input('sortKey') key: any;
  @Input('sortType') type: any;
  private toggleSort: boolean = false;

  constructor (private el: ElementRef, private renderer: Renderer) {
  }

  ngOnInit () {
    this.renderer.listen(this.el.nativeElement, 'click', (event) => {
      let parentNode = this.el.nativeElement.parentNode;
      let children   = parentNode.children;

      if (this.data && this.key && this.type) {
          if(this.type == 'string'){
            let sortedData: any = this.sortArray();
          }else if (this.type == 'number'){
            let sortedData: any = this.sortArrayNumber();
          }
       
      }
      this.toggleSort = !this.toggleSort;
    })
  }

  sortArray (): Array<any> {
    let tempArray: Array<any> = this.data;
    tempArray.sort((a, b) => {
      let aKey = a[this.key];
        let str1: string = a[this.key].toLowerCase();
        let str2: string = b[this.key].toLowerCase();
        if (this.toggleSort) {
          if (str1 < str2) {
            return -1;
          }
          if (str1 > str2) {
            return 1;
          }
        }
        else {
          if (str1 > str2) {
            return -1;
          }
          if (str1 < str2) {
            return 1;
          }
        }
      return 0;
    });
    return tempArray;
  }

  
  sortArrayNumber (): Array<any> {
    let tempArray: Array<any> = this.data;
    tempArray.sort((a, b) => {
      let aKey = a[this.key];
        let num1: number = a[this.key];
        let num2: number = b[this.key];
        if (this.toggleSort) {
          if (num1 < num2) {
            return -1;
          }
          if (num1 > num2) {
            return 1;
          }
        }
        else {
          if (num1 > num2) {
            return -1;
          }
          if (num1 < num2) {
            return 1;
          }
        }
      return 0;
    });
    return tempArray;
  }

  sortArrayDate (): Array<any> {
    let tempArray: Array<any> = this.data;
    tempArray.sort((a, b) => {
      let aKey = a[this.key];
        let date1: Date = new Date(a[this.key]);
        let date2: Date = new Date(b[this.key]);
        if (this.toggleSort) {
          if (date1 < date2) {
            return -1;
          }
          if (date1 > date2) {
            return 1;
          }
        }
        else {
          if (date1 > date2) {
            return -1;
          }
          if (date1 < date2) {
            return 1;
          }
        }
      return 0;
    });
    return tempArray;
  }
}