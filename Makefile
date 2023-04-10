BUILDDIR = build
LATEX = pdflatex
LATEX_FLAGS = --output-directory=$(BUILDDIR)

BASE = editorial
PDF = $(BASE).pdf
SOURCE = $(BASE).tex

.PHONY: clean all

all: compile

$(BUILDDIR):
	mkdir -p $(BUILDDIR)

compile: $(SOURCE) $(BUILDDIR)
	$(LATEX) $(LATEX_FLAGS) $(SOURCE)
	mv $(BUILDDIR)/$(PDF) .

clean:
	-rm -rf $(BUILDDIR)
