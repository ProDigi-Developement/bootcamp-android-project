/**Prabhjyot**/

package crats.mvcbaseproject.controller;

import java.util.ArrayList;
import crats.mvcbaseproject.model.User;

public interface IUserApi
{

        void fetchSuccess(ArrayList<User> list);
        void fetchFailure(String errorMessage);


}
