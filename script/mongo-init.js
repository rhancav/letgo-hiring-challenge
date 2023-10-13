db.createUser(
    {
        user: "ecav",
        pwd: "ecav123",
        roles: [
            {
                role: "readWrite",
                db: "booksdb"
            }
        ]
    }
);