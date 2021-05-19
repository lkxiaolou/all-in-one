package main

import (
	"github.com/labstack/echo/v4"
	"github.com/labstack/echo/v4/middleware"
	"os"
	"strconv"
)

type Response struct {
	Success bool `json:"success"`
	Message string `json:"message"`
	Port int `json:"port"`
	Uids []int `json:"uids"`
}

type Request struct {
	Uids []int `json:"uids"`
}

var port = 8888

func main() {

	e := echo.New()
	e.Use(middleware.Logger())
	e.Use(middleware.Recover())

	e.POST("/post", post)

	if len(os.Args) >= 2 {
		p, err := strconv.Atoi(os.Args[1])
		if err == nil {
			port = p
		}
	}
	e.Logger.Fatal(e.Start(":"+strconv.Itoa(port)))
}

func post(c echo.Context) error {
	req := Request{}
	err := c.Bind(&req)
	if err != nil {
		c.JSON(500, Response{
			Success: false,
			Port: port,
			Message: "bind body error",
		})
		return err
	}
	response := Response{
		Success: true,
		Port: port,
	}
	for _, uid := range req.Uids {
		response.Uids = append(response.Uids, uid + 100)
	}
	c.JSON(200, response)
	return nil
}
