
import { useState } from "react";
import { AxiosResponse } from "axios";
import axios from 'axios';

function AddView(){
    const [firstname, setFirstName] = useState("");
    const [lastname, setLastName] = useState("");
    const [message, setMessage] = useState("");

    axios.defaults.headers.common = {
        "Content-Type": "application/json"
    }

    let handleSubmit = async(e : React.ChangeEvent<HTMLFormElement>) => {
        e.preventDefault();
            // try {
            //     let res = await fetch("http://localhost:8080/add", {
            //       method: "POST",
            //       body: JSON.stringify({
            //         id:100, 
            //         firstName: firstname.toString(),
            //         lastName: lastname.toString(),
            //       }),
            //     });
            //     let resJson: JSON = await res.json();
            //     if (res.status === 200) {
            //       setFirstName("");
            //       setLastName("");
            //       setMessage("Person created successfully");
            //     } else {
            //       setMessage("Some error occured");
            //     }
            //   } catch (err) {
            //     console.log(err);
            //   }
            type CreatePersonResponse = {
                id: any,
                firstname: string,
                lastname: string
              }
              console.log("HERE",firstname,lastname);
              try {
                // 👇️ const data: CreateUserResponse
                const { data } = await axios.post<CreatePersonResponse>(
                  'http://localhost:8080/add?id=99&firstName='+firstname+"&lastName="+lastname,
                //   { firstName: firstname, lastName: lastname},
                  {
                    headers: {
                      'Content-Type': 'application/json',
                      Accept: 'application/json',
                    },
                  },
                );
                console.log(JSON.stringify(data, null, 4));
                setMessage("Person Added");
                window.location.href = '/';
                return data;
              } catch (error) {
                if (axios.isAxiosError(error)) {
                  console.log('error message: ', error.message);
                  // 👇️ error: AxiosError<any, any>
                  return error.message;
                } else {
                  console.log('unexpected error: ', error);
                  setMessage("Some error occured");
                  return 'An unexpected error occurred';
                }
            };
            };
    return(<>
            <form onSubmit={handleSubmit}>
            <h1>Add Person</h1>
            <input
              type="text"
              className="form-control"
              required
              value={firstname}
              placeholder="First Name"
              onChange={(e) => setFirstName(e.target.value)}
            />
            <input
              type="text"
              required
              value={lastname}
              placeholder="Last Name"
              onChange={(e) => setLastName(e.target.value)}
            />
            <button type="submit">Create</button>
            <div className="message">{message ? <p>{message}</p> : null}</div>
            </form>
    </>);
}
export default AddView;
