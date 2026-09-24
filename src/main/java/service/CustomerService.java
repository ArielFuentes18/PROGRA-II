/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Customer;

import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;

import org.apache.hc.client5.http.entity.EntityBuilder;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients; 

import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ContentType;

import java.io.InputStream;
import java.util.List;
/**
 *
 * @author UMG
 */
public class CustomerService {
    private static final String BASE_URL = "https://apirest2026-qcvp.onrender.com/api/customer";
    private static final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    
    //METODO GET
    public List<Customer> getCustomers() throws Exception{
        try(CloseableHttpClient client = HttpClients.createDefault()){   //esta levantando el browser
            HttpGet request = new HttpGet(BASE_URL);  //nueva request y ponemos el URI
            ClassicHttpResponse response = client.execute(request); //execute para mandarlo y lo manda al response
            int statusCode = response.getCode();
            if(statusCode != 200){
                throw new Exception("Error GET. Codigo HTTP:" + statusCode);
            }
            InputStream is = response.getEntity().getContent();
            return mapper.readValue(is, new TypeReference<List<Customer>> () {} 
            );
        }
    }
    
    // POST - crear cliente
    public Customer createCustomer(Customer c) throws Exception {

        c.setIngreso(java.time.Instant.now().toString());

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpPost request = new HttpPost(BASE_URL + "/create");

            String json = mapper.writeValueAsString(c);

            request.setEntity(
                    EntityBuilder.create()
                            .setText(json)
                            .setContentType(ContentType.APPLICATION_JSON)
                            .build()
            );

            ClassicHttpResponse response = client.execute(request);

            int statusCode = response.getCode();

            if (statusCode != 200 && statusCode != 201) {
                throw new Exception("Error POST. Código HTTP: " + statusCode);
            }

            InputStream is = response.getEntity().getContent();

            return mapper.readValue(is, Customer.class);
        }
    }

    // PUT - modificar cliente
    public Customer updateCustomer(int id, Customer c) throws Exception {

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpPut request = new HttpPut(BASE_URL + "/update/" + id);

            String json = mapper.writeValueAsString(c);

            request.setEntity(
                    EntityBuilder.create()
                            .setText(json)
                            .setContentType(ContentType.APPLICATION_JSON)
                            .build()
            );

            ClassicHttpResponse response = client.execute(request);

            int statusCode = response.getCode();

            if (statusCode != 200) {
                throw new Exception("Error PUT. Código HTTP: " + statusCode);
            }

            InputStream is = response.getEntity().getContent();

            return mapper.readValue(is, Customer.class);
        }
    }

    // DELETE - eliminar cliente
    public void deleteCustomer(int id) throws Exception {

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpDelete request = new HttpDelete(BASE_URL + "/delete/" + id);

            ClassicHttpResponse response = client.execute(request);

            int statusCode = response.getCode();

            if (statusCode != 200 && statusCode != 204) {
                throw new Exception("Error DELETE. Código HTTP: " + statusCode);
            }
        }
    }
}
   
