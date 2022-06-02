import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';

const authRoutes: Routes = [
    {   path:'',
        children:[
            {
                path:'',
                redirectTo:'/login',
                pathMatch:'full'

            },
            {
                path:'login',
                component:LoginComponent
            },
            {
                path:'register',
                component:RegisterComponent
            }
        ]
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(authRoutes)
    ],
    exports: [
        RouterModule
    ]
})
export class AuthenticationRoutingModule { }