package main

import (
	"bytes"
	"fmt"
	"go/ast"
	"go/parser"
	"go/printer"
	"go/token"
	"os"
	"strings"
)

const before = "fmt.Println(\"before\")"

func main() {
	fset := token.NewFileSet()
	pwd, _ := os.Getwd()
	file, err := parser.ParseFile(fset, pwd+"/go-in-one/samples/tree/aop.go", nil, parser.ParseComments)
	if err != nil {
		panic(err)
	}

	exprInsert, err := parser.ParseExpr(before)
	if err != nil {
		panic(err)
	}

	decls := make([]ast.Decl, 0, len(file.Decls))

	cmap := ast.NewCommentMap(fset, file, file.Comments)

	for _, decl := range file.Decls {
		fd, ok := decl.(*ast.FuncDecl)
		if ok {
			if cs, ok := cmap[fd]; ok {
				for _, cg := range cs {
					for _, c := range cg.List {
						if ok {
							if fd.Name.Name == "execute" {
								stats := make([]ast.Stmt, 0, len(fd.Body.List)+1)
								stats = append(stats, &ast.ExprStmt{
									X: exprInsert,
								})
								stats = append(stats, fd.Body.List...)
								fd.Body.List = stats
								decls = append(decls, fd)
								continue
							} else if strings.HasPrefix(c.Text, "// before:") {
								txt := strings.TrimPrefix(c.Text, "// before:")
								ei, err := parser.ParseExpr(txt)
								if err == nil {
									stats := make([]ast.Stmt, 0, len(fd.Body.List)+1)
									stats = append(stats, &ast.ExprStmt{
										X: ei,
									})
									stats = append(stats, fd.Body.List...)
									fd.Body.List = stats
									decls = append(decls, fd)
									continue
								}
							}
						}
					}
				}
			} else {
				decls = append(decls, decl)
			}
		} else {
			decls = append(decls, decl)
		}
	}

	file.Decls = decls

	var cfg printer.Config
	var buf bytes.Buffer

	cfg.Fprint(&buf, fset, file)

	fmt.Printf(buf.String())
}
