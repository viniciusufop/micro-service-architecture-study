db.createUser(
    {
        user: "pedido",
        pwd: "1234",
        roles: [
            {
                role: "readWrite",
                db: "pedido"
            }
        ]
    }
);
