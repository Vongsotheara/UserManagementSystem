1. <T> means "any type"  is a placeholder for any class.
 You decide what T is when you use it.
		PageDTO<T>
		        ↑
		        └── T is just a placeholder
		            you replace T with the actual type you want
		
		PageDTO<UserResponseDTO>
		        ↑
		        └── T is now UserResponseDTO
		            so List<T> becomes List<UserResponseDTO>

2. ResponseEntity: You use ResponseEntity because error handling requires
 setting the correct HTTP status code. Just returning ErrorResponse 
 alone would always send 200 OK, which would confuse clients consuming your API.           

***ResponseEntity gives you full control over the entire HTTP response, not just the body.

3. Soft delete: Soft delete means not actually deleting from the database. 
Instead you just mark the user as deleted with a flag.
	- User: deleted → true/false flag (is this user deleted?)
			deletedAt → when was it deleted?
	- UserRepository: findAll() returns all users including deleted ones. 
		We need new methods that only return users where deleted = false.
	- UserServiceImpl: deleteUser calls repo.deleteById() which permanently removes the row.
		 We need to change it to just set deleted = true instead. 
	- UserController: We need a new endpoint so you can restore a deleted user: 
						PATCH /api/users/1/restore
						
4. Add implementation on Spring Security

- Using JwtUtil is the tool that creates and reads the tokens for how we can prove the identity
after the login proccess. Instead of sending username/password on every request, the client sends this tokens.

- CustomUserDetailsService: Spring Security doesn't know about your User entity. It works with its own UserDetails interface. 
This class is the bridge, it loads your user from the database and converts it into the format Spring Security understands.

- JwtAuthFilter: Every single HTTP request passes through this filter before hitting your controller. It reads the JWT from the Authorization header, validates it, and tells Spring Security who the user is. 
Think of it as the bouncer at the door.

- SecurityConfig: This is the master configuration. It answers three questions: 
(1) which routes are public vs protected,
(2) what password encoder to use, 
(3) how to plug in your JwtAuthFilter.