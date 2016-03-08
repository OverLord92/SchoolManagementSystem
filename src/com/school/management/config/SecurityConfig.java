package com.school.management.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void confugureGlobal(AuthenticationManagerBuilder auth) throws Exception {		

		auth.jdbcAuthentication().passwordEncoder(passwordEncoder())
			.dataSource(dataSource)
			.usersByUsernameQuery(
					"SELECT username, password, enabled FROM user WHERE BINARY username=?")
			.authoritiesByUsernameQuery(
					"SELECT username, authority from user WHERE BINARY username=?")
			.rolePrefix("ROLE_");
		
	}
		
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http
				.authorizeRequests()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/home").permitAll()
				.antMatchers("/studentAccount").hasRole("STUDENT")
				.antMatchers("/requestCourse/*").hasRole("STUDENT")
				.antMatchers("/teacherAccount").hasRole("TEACHER")
				.antMatchers("/getStudentsCourse/*").hasRole("TEACHER")
				.antMatchers("/addAbsenceToUser").hasRole("TEACHER")
				.antMatchers("/addGradeToUser").hasRole("TEACHER")
				.antMatchers("/adminPage").hasRole("ADMIN")
				.antMatchers("/register").hasRole("ADMIN")
				.antMatchers("/registerStudent").hasRole("ADMIN")
				.antMatchers("/registerTeacher").hasRole("ADMIN")
				.antMatchers("/registerAdmin").hasRole("ADMIN")
				.antMatchers("/addCourse").hasRole("ADMIN")
				.antMatchers("/getCoursesWithoutTeachers").hasRole("ADMIN")
				.antMatchers("/assignCourseToTeacher").hasRole("ADMIN")
				.antMatchers("/approveCourserequest").hasRole("ADMIN")
				.antMatchers("/errorPages/*").permitAll()
				.anyRequest().denyAll()
			.and()
				.formLogin()
				.loginPage("/login")
				  .usernameParameter("username")
				  .passwordParameter("password")	
				.defaultSuccessUrl("/")
				.failureUrl("/login?loginerror=true")
			.and()
				.exceptionHandling().accessDeniedPage("/accessDenied")
			.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/");
	}
	
	@Bean
	public StandardPasswordEncoder passwordEncoder(){
		return new StandardPasswordEncoder();
	}
		
}