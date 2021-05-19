package unit

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestAdd(t *testing.T) {
	assert.Equal(t, 10, add(5, 5))
}

func TestSub(t *testing.T) {
	assert.Equal(t, 0, sub(5, 5))
}

func BenchmarkAdd(b *testing.B) {
	for i:= 0; i < b.N; i++ {
		add(5, 5)
	}
}
