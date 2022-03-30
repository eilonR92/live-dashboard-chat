import {Observable, of} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {User} from "./user";
import {environment} from "../environments/environment";
import {LoginForm} from "./login-form";
import {SignupForm} from "./signup-form";


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = `${environment.apiBseUrl}/users`;


  constructor(private http: HttpClient) {
  }

  public getUsers(): Observable<User[]> {


    return this.http.get<User[]>(this.baseUrl);
  }

  public addUser(user: User): Observable<User> {

    return this.http.post<User>(this.baseUrl, user);
  }

  public login(loginForm: LoginForm): Observable<string> {

    return this.http.post<string>(this.baseUrl+'/login', loginForm);
  }

  public logout(userName: string): Observable<string>{
    return this.http.post<string>(this.baseUrl+'/logout', userName)
  }

  public signup(signupForm: SignupForm): Observable<string> {
    console.log("user service signup");
    console.log(signupForm);
    const response = this.http.post<string>(this.baseUrl, signupForm);
    return response
  }
}
