import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.softserve.taf.models.enums.HttpStatus;
import com.softserve.taf.models.placeholder.user.UserDto;
import com.softserve.taf.services.common.AbstractWebEndpoint;

/**
 * UserEndpoint class represents an endpoint for managing users.
 * It extends the AbstractWebEndpoint class.
 */
public class UserEndpoint extends AbstractWebEndpoint {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String USERS_END = "/users";
    private static final String USERS_RESOURCE_END = "/users/{userID}";

    /**
     * Constructs a new UserEndpoint with the specified RequestSpecification.
     *
     * @param specification the RequestSpecification object to be used
     */
    public UserEndpoint(RequestSpecification specification) {
        super(specification);
    }

    /**
     * Creates a new user with the given UserDto and returns the created user.
     *
     * @param userDto the UserDto object representing the user to be created
     * @return the created UserDto object
     */
    public UserDto create(UserDto userDto) {
        return create(userDto, HttpStatus.CREATED)
                .extract().as(UserDto.class);
    }

    /**
     * Creates a new user with the given UserDto and returns the response in a ValidatableResponse object.
     *
     * @param userDto the UserDto object representing the user to be created
     * @param status the HttpStatus to be set for the response
     * @return the ValidatableResponse object representing the response
     */
    public ValidatableResponse create(UserDto userDto, HttpStatus status) {
        LOGGER.info("Create new User");
        return post(
                this.specification,
                USERS_END,
                userDto)
                .statusCode(status.getCode());
    }

    /**
     * Updates the user with the given ID using the provided UserDto and returns the updated user.
     *
     * @param id the ID of the user to be updated
     * @param userDto the UserDto object representing the updated user
     * @return the updated UserDto object
     */
    public UserDto update(int id, UserDto userDto) {
        return update(userDto, id, HttpStatus.OK)
                .extract().as(UserDto.class);
    }

    /**
     * Updates the user with the given ID using the provided UserDto and returns the response
     * in a ValidatableResponse object.
     *
     * @param userDto the UserDto object representing the updated user
     * @param id the ID of the user to be updated
     * @param status the HttpStatus to be set for the response
     * @return the ValidatableResponse object representing the response
     */
    public ValidatableResponse update(UserDto userDto, int id, HttpStatus status) {
        LOGGER.info("Update User by id [{}]", id);
        return put(
                this.specification,
                USERS_RESOURCE_END,
                userDto,
                id)
                .statusCode(status.getCode());
    }

    /**
     * Retrieves the user with the given ID.
     *
     * @param id the ID of the user to retrieve
     * @return the UserDto object representing the retrieved user
     */
    public UserDto getById(String id) {
        return getById(id, HttpStatus.OK)
                .extract().as(UserDto.class);
    }

    /**
     * Retrieves the user with the given ID and returns the response in a ValidatableResponse object.
     *
     * @param id the ID of the user to retrieve
     * @param status the HttpStatus to be set for the response
     * @return the ValidatableResponse object representing the response
     */
    public ValidatableResponse getById(String id, HttpStatus status) {
        LOGGER.info("Get User by id [{}]", id);
        return get(
                this.specification,
                USERS_RESOURCE_END,
                id)
                .statusCode(status.getCode());
    }

    /**
     * Retrieves all users.
     *
     * @return a list of UserDto objects representing all users
     */
    public List<UserDto> getAll() {
        return List.of(getAll(HttpStatus.OK).extract().as(UserDto[].class));
    }

    /**
     * Retrieves all users and returns the response in a ValidatableResponse object.
     *
     * @param status the HttpStatus to be set for the response
     * @return the ValidatableResponse object representing the response
     */
    public ValidatableResponse getAll(HttpStatus status) {
        LOGGER.info("Get all Users");
        ValidatableResponse response = get(this.specification, USERS_END);
        response.statusCode(status.getCode());
        return response;
    }

}