package com.proyecto.v1.model.util;

import java.util.*;

/**
 * Implementación personalizada de la interfaz List<E> basada en un arreglo dinámico.
 *
 * Esta clase proporciona una alternativa a ArrayList con funcionalidad básica pero completa.
 * El almacenamiento interno utiliza un Object[] que crece automáticamente al doble de su
 * capacidad cuando se alcanza el límite actual.
 *
 * Características principales:
 * - Acceso aleatorio en O(1) mediante get/set
 * - Inserción/eliminación al final en O(1) amortizado
 * - Inserción/eliminación en posición arbitraria en O(n)
 * - Búsqueda lineal en O(n)
 *
 * Thread-safety: Esta implementación NO es thread-safe. Si se requiere acceso concurrente,
 * debe sincronizarse externamente o usar Collections.synchronizedList().
 *
 * @param <E> el tipo de elementos en esta lista
 * @author Equipo de Desarrollo
 * @version 1.0
 */
public class ListaPersonalizada<E> implements List<E> {

    /**
     * Capacidad inicial del arreglo interno cuando se usa el constructor sin argumentos.
     * Este valor representa un balance entre uso de memoria y reasignaciones frecuentes.
     */
    private static final int CAPACIDAD_INICIAL = 10;

    /**
     * Arreglo interno que almacena los elementos de la lista.
     * Se mantiene como Object[] debido a las limitaciones de Java con arreglos genéricos.
     * El tamaño del arreglo puede ser mayor que el número de elementos en la lista.
     */
    private Object[] elementos;

    /**
     * Número actual de elementos en la lista (no confundir con elementos.length).
     * Invariante: 0 <= tamanio <= elementos.length
     */
    private int tamanio;

    /**
     * Constructor por defecto. Inicializa la lista con capacidad para 10 elementos.
     */
    public ListaPersonalizada() {
        this.elementos = new Object[CAPACIDAD_INICIAL];
        this.tamanio = 0;
    }

    /**
     * Constructor con capacidad inicial especificada.
     * Útil cuando se conoce de antemano el tamaño aproximado para evitar reasignaciones.
     *
     * @param capacidadInicial capacidad inicial del arreglo interno
     * @throws IllegalArgumentException si capacidadInicial es negativa
     */
    public ListaPersonalizada(int capacidadInicial) {
        if (capacidadInicial < 0) {
            throw new IllegalArgumentException("Capacidad inicial negativa: " + capacidadInicial);
        }
        this.elementos = new Object[capacidadInicial];
        this.tamanio = 0;
    }

    /**
     * Retorna el número de elementos en la lista.
     * Complejidad temporal: O(1)
     */
    @Override
    public int size() {
        return tamanio;
    }

    /**
     * Verifica si la lista está vacía.
     * Complejidad temporal: O(1)
     */
    @Override
    public boolean isEmpty() {
        return tamanio == 0;
    }

    /**
     * Agrega un elemento al final de la lista.
     * Si la capacidad actual no es suficiente, el arreglo interno se expande automáticamente.
     *
     * Complejidad temporal: O(1) amortizado, O(n) en el peor caso cuando se requiere expansión
     *
     * @param elemento el elemento a agregar (puede ser null)
     * @return siempre true, conforme al contrato de List.add()
     */
    @Override
    public boolean add(E elemento) {
        asegurarCapacidad();
        elementos[tamanio++] = elemento;
        return true;
    }

    /**
     * Inserta un elemento en la posición especificada.
     * Los elementos desde la posición hacia adelante se desplazan una posición a la derecha.
     *
     * Complejidad temporal: O(n) debido al desplazamiento de elementos
     *
     * @param indice posición donde insertar (0 <= indice <= tamanio)
     * @param elemento el elemento a insertar
     * @throws IndexOutOfBoundsException si el índice está fuera del rango válido
     */
    @Override
    public void add(int indice, E elemento) {
        verificarIndiceParaAgregar(indice);
        asegurarCapacidad();

        // System.arraycopy es más eficiente que un bucle manual para desplazar elementos
        System.arraycopy(elementos, indice, elementos, indice + 1, tamanio - indice);
        elementos[indice] = elemento;
        tamanio++;
    }

    /**
     * Retorna el elemento en la posición especificada.
     * Complejidad temporal: O(1)
     *
     * @param indice posición del elemento a retornar
     * @return el elemento en la posición especificada
     * @throws IndexOutOfBoundsException si el índice está fuera del rango [0, tamanio)
     */
    @Override
    @SuppressWarnings("unchecked")
    public E get(int indice) {
        verificarIndice(indice);
        return (E) elementos[indice];
    }

    /**
     * Reemplaza el elemento en la posición especificada.
     * Complejidad temporal: O(1)
     *
     * @param indice posición del elemento a reemplazar
     * @param elemento nuevo elemento a almacenar
     * @return el elemento que estaba previamente en esa posición
     * @throws IndexOutOfBoundsException si el índice está fuera del rango [0, tamanio)
     */
    @Override
    @SuppressWarnings("unchecked")
    public E set(int indice, E elemento) {
        verificarIndice(indice);
        E valorAnterior = (E) elementos[indice];
        elementos[indice] = elemento;
        return valorAnterior;
    }

    /**
     * Elimina el elemento en la posición especificada.
     * Los elementos posteriores se desplazan una posición a la izquierda.
     * La referencia en la última posición se establece a null para permitir GC.
     *
     * Complejidad temporal: O(n) debido al desplazamiento de elementos
     *
     * @param indice posición del elemento a eliminar
     * @return el elemento que fue eliminado
     * @throws IndexOutOfBoundsException si el índice está fuera del rango [0, tamanio)
     */
    @Override
    @SuppressWarnings("unchecked")
    public E remove(int indice) {
        verificarIndice(indice);
        E valorAnterior = (E) elementos[indice];

        int numeroMovidos = tamanio - indice - 1;
        if (numeroMovidos > 0) {
            System.arraycopy(elementos, indice + 1, elementos, indice, numeroMovidos);
        }
        // Importante: establecer null para permitir que el GC libere la referencia
        elementos[--tamanio] = null;

        return valorAnterior;
    }

    /**
     * Elimina la primera ocurrencia del objeto especificado.
     * La comparación se realiza usando Objects.equals() para manejar correctamente null.
     *
     * Complejidad temporal: O(n) para búsqueda + O(n) para desplazamiento
     *
     * @param objeto elemento a eliminar
     * @return true si se encontró y eliminó el elemento, false en caso contrario
     */
    @Override
    public boolean remove(Object objeto) {
        for (int i = 0; i < tamanio; i++) {
            if (Objects.equals(objeto, elementos[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si la lista contiene el objeto especificado.
     * Complejidad temporal: O(n)
     *
     * @param objeto elemento cuya presencia se va a verificar
     * @return true si el elemento está en la lista
     */
    @Override
    public boolean contains(Object objeto) {
        return indexOf(objeto) >= 0;
    }

    /**
     * Retorna el índice de la primera ocurrencia del objeto especificado.
     * Usa Objects.equals() para la comparación, permitiendo búsqueda de null.
     *
     * Complejidad temporal: O(n)
     *
     * @param objeto elemento a buscar
     * @return índice del elemento, o -1 si no se encuentra
     */
    @Override
    public int indexOf(Object objeto) {
        for (int i = 0; i < tamanio; i++) {
            if (Objects.equals(objeto, elementos[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Retorna el índice de la última ocurrencia del objeto especificado.
     * La búsqueda se realiza desde el final hacia el inicio.
     *
     * Complejidad temporal: O(n)
     *
     * @param objeto elemento a buscar
     * @return índice del elemento, o -1 si no se encuentra
     */
    @Override
    public int lastIndexOf(Object objeto) {
        for (int i = tamanio - 1; i >= 0; i--) {
            if (Objects.equals(objeto, elementos[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Elimina todos los elementos de la lista.
     * Establece todas las referencias a null para permitir garbage collection.
     * La capacidad del arreglo interno no se reduce.
     *
     * Complejidad temporal: O(n)
     */
    @Override
    public void clear() {
        // Recorrer y establecer null es importante para liberar referencias
        for (int i = 0; i < tamanio; i++) {
            elementos[i] = null;
        }
        tamanio = 0;
    }

    /**
     * Agrega todos los elementos de la colección especificada al final de esta lista.
     * El orden de inserción es el determinado por el iterador de la colección.
     *
     * Complejidad temporal: O(m) donde m es el tamaño de la colección
     *
     * @param coleccion colección con elementos a agregar
     * @return true si la lista cambió como resultado de la operación
     */
    @Override
    public boolean addAll(Collection<? extends E> coleccion) {
        Object[] arreglo = coleccion.toArray();
        int numeroNuevos = arreglo.length;
        asegurarCapacidad(tamanio + numeroNuevos);
        System.arraycopy(arreglo, 0, elementos, tamanio, numeroNuevos);
        tamanio += numeroNuevos;
        return numeroNuevos != 0;
    }

    /**
     * Inserta todos los elementos de la colección en la posición especificada.
     * Los elementos existentes se desplazan hacia la derecha.
     *
     * Complejidad temporal: O(n + m) donde n es tamanio y m es tamaño de colección
     *
     * @param indice posición donde comenzar la inserción
     * @param coleccion colección con elementos a insertar
     * @return true si la lista cambió como resultado de la operación
     * @throws IndexOutOfBoundsException si el índice está fuera del rango válido
     */
    @Override
    public boolean addAll(int indice, Collection<? extends E> coleccion) {
        verificarIndiceParaAgregar(indice);
        Object[] arreglo = coleccion.toArray();
        int numeroNuevos = arreglo.length;
        asegurarCapacidad(tamanio + numeroNuevos);

        int numeroMovidos = tamanio - indice;
        if (numeroMovidos > 0) {
            System.arraycopy(elementos, indice, elementos, indice + numeroNuevos, numeroMovidos);
        }
        System.arraycopy(arreglo, 0, elementos, indice, numeroNuevos);
        tamanio += numeroNuevos;
        return numeroNuevos != 0;
    }

    /**
     * Retorna un arreglo que contiene todos los elementos de la lista.
     * El arreglo retornado es una copia, modificarlo no afecta la lista.
     *
     * Complejidad temporal: O(n)
     *
     * @return arreglo con todos los elementos
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementos, tamanio);
    }

    /**
     * Retorna un arreglo tipado que contiene todos los elementos de la lista.
     * Si el arreglo proporcionado es suficientemente grande, se usa ese.
     * De lo contrario, se crea un nuevo arreglo del tipo apropiado.
     *
     * Complejidad temporal: O(n)
     *
     * @param arreglo arreglo destino si es suficientemente grande
     * @return arreglo con todos los elementos
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] arreglo) {
        if (arreglo.length < tamanio) {
            // Crear nuevo arreglo del tipo runtime apropiado
            return (T[]) Arrays.copyOf(elementos, tamanio, arreglo.getClass());
        }
        System.arraycopy(elementos, 0, arreglo, 0, tamanio);
        if (arreglo.length > tamanio) {
            // Marcar el fin de los elementos válidos según contrato de Collection
            arreglo[tamanio] = null;
        }
        return arreglo;
    }

    /**
     * Retorna un iterador sobre los elementos de la lista en secuencia.
     * El iterador soporta la operación remove().
     *
     * Nota: El iterador no es fail-fast. Si la lista se modifica estructuralmente
     * durante la iteración (excepto mediante el propio iterador), el comportamiento
     * es indefinido.
     *
     * @return iterador sobre los elementos
     */
    @Override
    public Iterator<E> iterator() {
        return new IteradorPersonalizado();
    }

    /**
     * Retorna un iterador de lista comenzando desde el inicio.
     *
     * @return iterador de lista comenzando en posición 0
     */
    @Override
    public ListIterator<E> listIterator() {
        return new IteradorListaPersonalizado(0);
    }

    /**
     * Retorna un iterador de lista comenzando en la posición especificada.
     *
     * @param indice posición inicial del iterador
     * @return iterador de lista comenzando en la posición especificada
     * @throws IndexOutOfBoundsException si el índice está fuera del rango válido
     */
    @Override
    public ListIterator<E> listIterator(int indice) {
        verificarIndiceParaAgregar(indice);
        return new IteradorListaPersonalizado(indice);
    }

    /**
     * Retorna una vista de la porción de esta lista entre los índices especificados.
     * La lista retornada es una nueva instancia independiente (no es una vista).
     *
     * Complejidad temporal: O(indiceHasta - indiceDesde)
     *
     * @param indiceDesde índice inicial inclusivo
     * @param indiceHasta índice final exclusivo
     * @return nueva lista con los elementos en el rango especificado
     * @throws IndexOutOfBoundsException si los índices están fuera de rango o indiceDesde > indiceHasta
     */
    @Override
    public List<E> subList(int indiceDesde, int indiceHasta) {
        if (indiceDesde < 0 || indiceHasta > tamanio || indiceDesde > indiceHasta) {
            throw new IndexOutOfBoundsException();
        }
        ListaPersonalizada<E> subLista = new ListaPersonalizada<>(indiceHasta - indiceDesde);
        for (int i = indiceDesde; i < indiceHasta; i++) {
            subLista.add(get(i));
        }
        return subLista;
    }

    /**
     * Verifica si la lista contiene todos los elementos de la colección especificada.
     * Complejidad temporal: O(n * m) donde n es tamanio y m es tamaño de colección
     *
     * @param coleccion colección a verificar
     * @return true si todos los elementos de la colección están en esta lista
     */
    @Override
    public boolean containsAll(Collection<?> coleccion) {
        for (Object elemento : coleccion) {
            if (!contains(elemento)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Elimina de esta lista todos los elementos que están en la colección especificada.
     * Se eliminan todas las ocurrencias de cada elemento.
     *
     * Complejidad temporal: O(n * m) en el peor caso
     *
     * @param coleccion colección con elementos a eliminar
     * @return true si la lista cambió como resultado de la operación
     */
    @Override
    public boolean removeAll(Collection<?> coleccion) {
        boolean modificado = false;
        for (Object elemento : coleccion) {
            // Eliminar todas las ocurrencias del elemento
            while (remove(elemento)) {
                modificado = true;
            }
        }
        return modificado;
    }

    /**
     * Retiene solo los elementos que están en la colección especificada.
     * Elimina todos los elementos que no están en la colección.
     *
     * Complejidad temporal: O(n * m) donde n es tamanio y m es tamaño de colección
     *
     * @param coleccion colección con elementos a retener
     * @return true si la lista cambió como resultado de la operación
     */
    @Override
    public boolean retainAll(Collection<?> coleccion) {
        boolean modificado = false;
        // Iterar desde el final para evitar problemas con índices al eliminar
        for (int i = tamanio - 1; i >= 0; i--) {
            if (!coleccion.contains(elementos[i])) {
                remove(i);
                modificado = true;
            }
        }
        return modificado;
    }

    // ========== MÉTODOS AUXILIARES PRIVADOS ==========

    /**
     * Asegura que hay capacidad para al menos un elemento más.
     * Invoca asegurarCapacidad(tamanio + 1).
     */
    private void asegurarCapacidad() {
        asegurarCapacidad(tamanio + 1);
    }

    /**
     * Asegura que el arreglo interno tiene al menos la capacidad especificada.
     * Si la capacidad actual es insuficiente, crea un nuevo arreglo con el doble
     * de capacidad (o la capacidad mínima requerida si es mayor).
     *
     * Estrategia de crecimiento: duplicar capacidad proporciona O(1) amortizado
     * para operaciones de inserción secuencial.
     *
     * @param capacidadMinima capacidad mínima requerida
     */
    private void asegurarCapacidad(int capacidadMinima) {
        if (capacidadMinima > elementos.length) {
            int nuevaCapacidad = elementos.length * 2;
            if (nuevaCapacidad < capacidadMinima) {
                nuevaCapacidad = capacidadMinima;
            }
            elementos = Arrays.copyOf(elementos, nuevaCapacidad);
        }
    }

    /**
     * Verifica que el índice esté en el rango válido [0, tamanio).
     * Lanza excepción si el índice está fuera de rango.
     *
     * @param indice índice a verificar
     * @throws IndexOutOfBoundsException si el índice es inválido
     */
    private void verificarIndice(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Índice: " + indice + ", Tamaño: " + tamanio);
        }
    }

    /**
     * Verifica que el índice esté en el rango válido [0, tamanio] para operaciones add.
     * Permite índice igual a tamanio (agregar al final).
     *
     * @param indice índice a verificar
     * @throws IndexOutOfBoundsException si el índice es inválido
     */
    private void verificarIndiceParaAgregar(int indice) {
        if (indice < 0 || indice > tamanio) {
            throw new IndexOutOfBoundsException("Índice: " + indice + ", Tamaño: " + tamanio);
        }
    }

    /**
     * Retorna una representación en String de la lista.
     * Formato: [elemento1, elemento2, elemento3]
     *
     * @return representación en String de la lista
     */
    @Override
    public String toString() {
        if (tamanio == 0) {
            return "[]";
        }
        StringBuilder constructor = new StringBuilder("[");
        for (int i = 0; i < tamanio; i++) {
            constructor.append(elementos[i]);
            if (i < tamanio - 1) {
                constructor.append(", ");
            }
        }
        constructor.append("]");
        return constructor.toString();
    }

    // ========== CLASES INTERNAS: ITERADORES ==========

    /**
     * Implementación de Iterator para recorrer los elementos secuencialmente.
     * Soporta la operación remove() para eliminar elementos durante la iteración.
     *
     * Advertencia: No es fail-fast. Las modificaciones estructurales directas
     * a la lista durante la iteración pueden causar comportamiento indefinido.
     */
    private class IteradorPersonalizado implements Iterator<E> {
        /**
         * Índice del siguiente elemento a retornar.
         */
        int cursor = 0;

        /**
         * Índice del último elemento retornado por next().
         * -1 si no se ha llamado next() o se llamó remove() después del último next().
         */
        int ultimoRetornado = -1;

        /**
         * Verifica si quedan elementos por iterar.
         */
        @Override
        public boolean hasNext() {
            return cursor < tamanio;
        }

        /**
         * Retorna el siguiente elemento en la iteración.
         *
         * @return el siguiente elemento
         * @throws NoSuchElementException si no hay más elementos
         */
        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            ultimoRetornado = cursor;
            return (E) elementos[cursor++];
        }

        /**
         * Elimina de la lista el último elemento retornado por next().
         * Solo puede llamarse una vez por cada llamada a next().
         *
         * @throws IllegalStateException si next() no ha sido llamado
         *         o remove() ya fue llamado después del último next()
         */
        @Override
        public void remove() {
            if (ultimoRetornado < 0) {
                throw new IllegalStateException();
            }
            ListaPersonalizada.this.remove(ultimoRetornado);
            cursor = ultimoRetornado;
            ultimoRetornado = -1;
        }
    }

    /**
     * Implementación de ListIterator que permite recorrido bidireccional
     * y modificación de elementos durante la iteración.
     *
     * Extiende IteradorPersonalizado y agrega funcionalidad para:
     * - Recorrer hacia atrás con previous()
     * - Modificar elementos con set()
     * - Insertar elementos con add()
     */
    private class IteradorListaPersonalizado extends IteradorPersonalizado implements ListIterator<E> {

        /**
         * Constructor que establece la posición inicial del iterador.
         *
         * @param indice posición inicial del cursor
         */
        IteradorListaPersonalizado(int indice) {
            cursor = indice;
        }

        /**
         * Verifica si hay un elemento anterior en la lista.
         */
        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        /**
         * Retorna el elemento anterior y mueve el cursor hacia atrás.
         *
         * @return el elemento anterior
         * @throws NoSuchElementException si no hay elemento anterior
         */
        @Override
        @SuppressWarnings("unchecked")
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            ultimoRetornado = --cursor;
            return (E) elementos[cursor];
        }

        /**
         * Retorna el índice del elemento que sería retornado por next().
         */
        @Override
        public int nextIndex() {
            return cursor;
        }

        /**
         * Retorna el índice del elemento que sería retornado por previous().
         */
        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        /**
         * Reemplaza el último elemento retornado por next() o previous().
         *
         * @param elemento el nuevo elemento
         * @throws IllegalStateException si no se ha llamado next()/previous()
         *         o se llamó add()/remove() después del último next()/previous()
         */
        @Override
        public void set(E elemento) {
            if (ultimoRetornado < 0) {
                throw new IllegalStateException();
            }
            ListaPersonalizada.this.set(ultimoRetornado, elemento);
        }

        /**
         * Inserta un elemento en la posición actual del cursor.
         * El elemento se inserta antes del elemento que sería retornado por next(),
         * y después del elemento que sería retornado por previous().
         *
         * @param elemento el elemento a insertar
         */
        @Override
        public void add(E elemento) {
            ListaPersonalizada.this.add(cursor++, elemento);
            ultimoRetornado = -1;
        }
    }
}