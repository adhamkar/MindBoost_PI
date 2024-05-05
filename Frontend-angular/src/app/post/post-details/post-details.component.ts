import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ActivatedRouteSnapshot, Router} from "@angular/router";

@Component({
  selector: 'app-post-details',
  templateUrl: './post-details.component.html',
  styleUrl: './post-details.component.css'
})
export class PostDetailsComponent implements OnInit{
  constructor(private router:ActivatedRoute,private router2:Router) {
  }
  public post={
    id: 1,
    date: '2024-12-12',
    content:  'Lorem Ipsum is simply dummy text of the printing and typesetting industry.' +
      'Lorem Ipsum is simply dummy text of the printing and typesetting industry' +
      'Lorem Ipsum is simply dummy text of the printing and typesetting industry..'
  };
  public id!:string;
  public comments:any=[
    {
      id: 1,
      date: '2024-12-12',
      content:  'Lorem Ipsum is simply dummy text of the printing and '
    },{
      id: 1,
      date: '2024-12-12',
      content:  'Lorem Ipsum is simply dummy text of the printing and '
    },{
      id: 1,
      date: '2024-12-12',
      content:  'Lorem Ipsum is simply dummy text of the printing and '
    },{
      id: 1,
      date: '2024-12-12',
      content:  'Lorem Ipsum is simply dummy text of the printing and '
    },{
      id: 1,
      date: '2024-12-12',
      content:  'Lorem Ipsum is simply dummy text of the printing and '
    }

  ]

  ngOnInit(): void {
    throw new Error('Method not implemented.');
    this.id = this.router.snapshot.params['id'];

  }

  gotoList(){
    this.router2.navigate(['user/posts']);
  }

}
