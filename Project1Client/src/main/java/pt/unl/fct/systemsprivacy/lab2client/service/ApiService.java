package pt.unl.fct.systemsprivacy.lab2client.service;


import pt.unl.fct.systemsprivacy.lab2client.model.CountRequest;
import pt.unl.fct.systemsprivacy.lab2client.model.CountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.List;

@Service
public class ApiService {
    private final RestClient restClient;

    public ApiService(RestClient restClient) {
        this.restClient = restClient;
    }

    /*
    public User[] getAllUsers() {
        return restClient.get().uri("/users").retrieve().body(User[].class);
    }

    public User getUser(Long id) {
        return restClient.get().uri("/users/{id}", id).retrieve().body(User.class);
    }

    public ResponseEntity<User> getUserEntity(Long id) {
        return restClient.get().uri("/users/{id}", id).retrieve().toEntity(User.class);
    }

    public ResponseEntity deleteUser(Long id) {
        return restClient.delete()
                .uri("/users/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<User> updateUserEntity(User user) {
        return restClient.put()
                .uri("/users/{id}", user.getId())
                .body(user)
                .retrieve()
                .toEntity(User.class);
    }

    public User updateUser(User user) {
        return restClient.put()
                .uri("/users/{id}", user.getId())
                .body(user)
                .retrieve()
                .body(User.class);
    }

    public Post[] getAllPosts() {
        return restClient.get().uri("/post").retrieve().body(Post[].class);
    }

    public Post getPost(Long id) {
        return restClient.get()
                .uri("/post/{id}", id)
                .retrieve()
                .body(Post.class);
    }

    public ResponseEntity<Post> getPostEntity(Long id) {
        return restClient.get()
                .uri("/post/{id}", id)
                .retrieve()
                .toEntity(Post.class);
    }

    public ResponseEntity deletePost(Long id) {
        return restClient.delete()
                .uri("/post/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }

    public UserFile[] getAllUserFiles() {
        return restClient.get()
                .uri("/userFiles")
                .retrieve()
                .body(UserFile[].class);
    }

    public UserFile getUserFile(Long id) {
        return restClient.get()
                .uri("/userFiles/{id}", id)
                .retrieve()
                .body(UserFile.class);
    }

    public ResponseEntity<UserFile> getUserFileEntity(Long id) {
        return restClient.get()
                .uri("/userFiles/{id}", id)
                .retrieve()
                .toEntity(UserFile.class);
    }

    public ResponseEntity deleteUserFile(Long id) {
        return restClient.delete()
                .uri("/userFiles/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }

    public LoginLog[] getAllLoginLogs() {
        return restClient.get()
                .uri("/loginLogs")
                .retrieve()
                .body(LoginLog[].class);
    }

    public LoginLog getLoginLog(Long id) {
        return restClient.get()
                .uri("/loginLogs/{id}", id)
                .retrieve()
                .body(LoginLog.class);
    }

    public ResponseEntity<LoginLog> getLoginLogEntity(Long id) {
        return restClient.get()
                .uri("/lgoinLogs/{id}", id)
                .retrieve()
                .toEntity(LoginLog.class);
    }
    */

    public CountResponse postCountResponse(CountRequest req) {
        return restClient.post()
                .uri("/meddata/count")
                .body(req)
                .retrieve()
                .body(CountResponse.class);
    }

    public CountResponse postAverageResponse(CountRequest req) {
        return restClient.post()
                .uri("/meddata/average")
                .body(req)
                .retrieve()
                .body(CountResponse.class);
    }

    public CountResponse postAverageGroupedResponse(CountRequest req) {
        return restClient.post()
                .uri("/meddata/groupedDiagnosis")
                .body(req)
                .retrieve()
                .body(CountResponse.class);
    }

}
