module.exports = app => {
    const producto = require("../controllers/producto.controller.js");
    var router = require("express").Router();
    // Create a new producto
    router.post("/create/", producto.create);
    // Retrieve all producto
    router.get("/", producto.findAll);
    // Retrieve all published producto
    router.get("/status", producto.findAllStatus);
    // Retrieve a single producto with id
    router.get("/:id", producto.findOne);
    // Update a producto with id
    router.put("/update/:id", producto.update);
    // Delete a producto with id
    router.delete("/delete/:id", producto.delete);
    // Delete all productos
    router.delete("/delete/", producto.deleteAll);
    // Podemos utilizar como una ocpion app.use("EndPoint",router" para simplicar el URI
    // Ej.  http://localhost:Puerto/api/producto/
    app.use("/api/producto", router);
};