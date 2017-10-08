package io.github.tiagorgt.vertx.api.service;

import io.github.tiagorgt.vertx.api.entity.User;
import io.github.tiagorgt.vertx.api.repository.UserDao;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by tiago on 07/10/2017.
 */
public class UserService {
    private UserDao userDao = UserDao.getInstance();

    public void list(Handler<AsyncResult<List<User>>> handler) {
        Future<List<User>> future = Future.future();
        future.setHandler(handler);

        try {
            List<User> result = userDao.findAll();
            future.complete(result);
        } catch (Throwable ex) {
            future.fail(ex);
        }
    }

    public void getByFilter(JsonObject filter, Handler<AsyncResult<List<User>>> handler){
        Future<List<User>> future = Future.future();
        future.setHandler(handler);

        try {
            List<User> result = userDao.getByFilter(filter);
            future.complete(result);
        } catch (Exception ex){
            future.fail(ex);
        }
    }

    public void getById(String cpf, Handler<AsyncResult<User>> handler) {
        Future<User> future = Future.future();
        future.setHandler(handler);

        try {
            User result = userDao.getById(cpf);
            future.complete(result);
        } catch (Throwable ex) {
            future.fail(ex);
        }
    }

    public void save(User newUser, Handler<AsyncResult<User>> handler) {
        Future<User> future = Future.future();
        future.setHandler(handler);

        try {
            User user = userDao.getById(newUser.getCpf());

            if (user != null) {
                future.fail("Usuário já incluído.");
                return;
            }
            newUser.setStatus("A");
            userDao.persist(newUser);
            future.complete();
        } catch (Throwable ex) {
            future.fail(ex);
        }
    }

    public void update(User user, Handler<AsyncResult<User>> handler) {
        Future<User> future = Future.future();
        future.setHandler(handler);

        try {
            userDao.merge(user);
            future.complete();
        } catch (Throwable ex) {
            future.fail(ex);
        }
    }

    public void remove(String cpf, Handler<AsyncResult<User>> handler) {
        Future<User> future = Future.future();
        future.setHandler(handler);

        try {
            userDao.removeById(cpf);
            future.complete();
        } catch (Throwable ex) {
            future.fail(ex);
        }
    }

}
