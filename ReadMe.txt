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