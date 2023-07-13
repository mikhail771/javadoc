import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.softserve.taf.models.enums.HttpStatus;
import com.softserve.taf.models.placeholder.comment.CommentDto;
import com.softserve.taf.services.common.AbstractWebEndpoint;

/**
 * CommentEndpoint class represents an endpopint for managing comments.
 * Extends the AbstractWebEndpoint class
 */
public class CommentEndpoint extends AbstractWebEndpoint {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String COMMENTS_END = "/comments";
    private static final String COMMENTS_RESOURCE_END = "/comments/{commentID}";

    /**
     * Constructs a new CommentEndpoint with the specified RequestSpecification
     *
     * @param specification RequestSpecification object
     */
    public CommentEndpoint(RequestSpecification specification) {
        super(specification);
    }

    /**
     * Creates a new comment with the given CommentDto and returns the created comment
     *
     * @param commentDto the CommentDto object representing the comment to be created
     * @return the created CommentDto object
     */
    public CommentDto create(CommentDto commentDto) {
        return create(commentDto, HttpStatus.CREATED)
                .extract().as(CommentDto.class);
    }

    /**
     * Creates a new comment with the given CommentDto and returns the response in a ValidatableResponse object
     *
     * @param commentDto the CommentDto object representing the comment to be created
     * @param status the HttpStatus to be set for the response
     * @return the ValidatableResponse object representing the comment to be created
     */
    public ValidatableResponse create(CommentDto commentDto, HttpStatus status) {
        LOGGER.info("Create new Comment");
        return post(
                this.specification,
                COMMENTS_END,
                commentDto)
                .statusCode(status.getCode());
    }

    /**
     * Updates the comment with the given ID using the provided CommentDto and returns the response in a ValidatableResponse object
     *
     * @param id the ID of the comment to be updated
     * @param commentDto the CommentDto pbject representing the updated comment
     * @return the updated CommentDto object
     */
    public CommentDto update(int id, CommentDto commentDto) {
        return update(commentDto, id, HttpStatus.OK)
                .extract().as(CommentDto.class);
    }

    /**
     * Updates the comment with the given ID using the provided CommentDto and returns the response
     * in a ValidatableResponse object.
     *
     * @param commentDto the CommentDto object representing the updated comment
     * @param id the ID of the comment to be updated
     * @param status the HttpStatus to be set for the response
     * @return the ValidatableResponse object representing the response
     */
    public ValidatableResponse update(CommentDto commentDto, int id, HttpStatus status) {
        LOGGER.info("Update Comment by id [{}]", id);
        return put(
                this.specification,
                COMMENTS_RESOURCE_END,
                commentDto,
                id)
                .statusCode(status.getCode());
    }

    /**
     * Retrieves the comment with the given ID
     *
     * @param id the ID of the comment to retrieve
     * @return the CommentDto object representing the retrieved comment
     */
    public CommentDto getById(int id) {
        return getById(id, HttpStatus.OK)
                .extract().as(CommentDto.class);
    }

    /**
     * Retrieves the comment with the given ID and returns the response in a ValidatableResponse object.
     *
     * @param id the ID of the comment to retrieve
     * @param status the HttpStatus to be set for the response
     * @return the ValidatableResponse object representing the response
     */
    public ValidatableResponse getById(int id, HttpStatus status) {
        LOGGER.info("Get Comment by id [{}]", id);
        return get(
                this.specification,
                COMMENTS_RESOURCE_END,
                String.valueOf(id))
                .statusCode(status.getCode());
    }

    /**
     * Retrieves all comments.
     *
     * @return a list of CommentDto objects representing all comments
     */
    public List<CommentDto> getAll() {
        return List.of(getAll(HttpStatus.OK).extract().as(CommentDto[].class));
    }

    /**
     * Retrieves all comments and returns the response in a ValidatableResponse object.
     *
     * @param status the HttpStatus to be set for the response
     * @return the ValidatableResponse object representing the response
     */
    public ValidatableResponse getAll(HttpStatus status) {
        LOGGER.info("Get all Comments");
        ValidatableResponse response = get(this.specification, COMMENTS_END);
        response.statusCode(status.getCode());
        return response;
    }


}
